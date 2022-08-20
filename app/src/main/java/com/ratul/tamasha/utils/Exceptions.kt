package com.ratul.tamasha.utils

import java.io.IOException

class Exceptions {

    class ApiException(message : String) : IOException(message)

    class NoNetworkException(message: String) : IOException(message)

}