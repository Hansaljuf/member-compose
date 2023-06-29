package com.capstone.maroonlabkom.di

import com.capstone.maroonlabkom.data.MemberRepository

object Injection {
    fun provideRepository():MemberRepository{
        return MemberRepository.getInstance()
    }
}