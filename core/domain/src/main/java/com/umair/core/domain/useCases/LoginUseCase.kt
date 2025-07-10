package com.umair.core.domain.useCases

import com.umair.core.domain.BaseUseCase

class LoginUseCase : BaseUseCase() {
    var result = "Result"
    fun getResults() : String = result

}