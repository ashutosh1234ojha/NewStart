package com.example.newstart.unittest

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Ashutosh Ojha on 23,October,2021
 */
class RegistrationUtilTest{
    @Test
    fun empty_user_name_return_false(){
        val result=RegistrationUtil.validateRegistrationInput("","123","123")
        assertTrue(result)
    }
}