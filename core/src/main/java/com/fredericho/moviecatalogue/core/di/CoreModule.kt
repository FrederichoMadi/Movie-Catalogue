package com.fredericho.moviecatalogue.core.di

import androidx.room.Room
import com.fredericho.moviecatalogue.core.data.source.MovieRepository
import com.fredericho.moviecatalogue.core.data.source.local.LocalDataSource
import com.fredericho.moviecatalogue.core.data.source.local.room.MoviesDatabase
import com.fredericho.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.fredericho.moviecatalogue.core.data.source.remote.network.ApiService
import com.fredericho.moviecatalogue.core.domain.repository.IMovieRepository
import com.fredericho.moviecatalogue.core.utils.AppExecutor
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MoviesDatabase>().movieDao() }
    single {
        val passphrase : ByteArray = SQLiteDatabase.getBytes("fredericho".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MoviesDatabase::class.java, "movie.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val netWorkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module{
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutor() }
    single<IMovieRepository> { MovieRepository(get(), get(), get())
    }
}