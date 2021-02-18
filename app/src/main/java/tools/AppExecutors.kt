package tools

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

class AppExecutors {
    private object Holder{
        val instance : AppExecutors = AppExecutors()
    }
    companion object{
        val instance:AppExecutors by lazy{ Holder.instance}
    }

    private val netWorkIO = Executors.newScheduledThreadPool(3)

    fun getNetworkIO():ScheduledExecutorService = netWorkIO
}