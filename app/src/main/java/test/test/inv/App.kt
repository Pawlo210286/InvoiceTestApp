package test.test.inv

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import test.test.inv.di.AppModule

class App : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(AppModule.get(this@App))
    }
    override val kodeinTrigger = KodeinTrigger()

    override fun onCreate() {
        super.onCreate()
        kodeinTrigger.trigger()
    }
}