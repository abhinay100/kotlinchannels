package com.example.kotlinchannels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


/**
 * Created by Abhinay on 03/12/24.
 *
 *
 */
class MainViewModel : ViewModel() {

    sealed class MyEvent {
        data class ErrorEvent(val message: String) : MyEvent()
    }

    private val eventChannel = Channel<MyEvent>()
    val eventFlow = eventChannel.receiveAsFlow()

    fun triggerEvent() = viewModelScope.launch {
        eventChannel.send(MyEvent.ErrorEvent("This is an error"))
    }
}
