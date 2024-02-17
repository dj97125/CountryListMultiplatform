package data

data class StateAction<out T>(val status: Status, val data: T?, val error: onError?){

    companion object {
        //Handles success
        fun <T> success(data: T): StateAction<T> = StateAction(
            status = Status.SUCCESS, data = data, error = null)
        //Handles Loading
        fun <T> loading(data: T?=null): StateAction<T> = StateAction(
            status = Status.LOADING, data = data, error = null)
        //Handles Error
        fun <T> error(data: T?=null, error: onError?=null): StateAction<T> = StateAction(
            status = Status.ERROR, data = data, error=error)
        fun<T> idle(data:T?) = StateAction (status = Status.NONE, data = data, error = null)

    }
    enum class Status {
        NONE,
        SUCCESS,
        ERROR,
        LOADING
    }
}

data class onError(val code:Int = -1,  val message:String?=null)