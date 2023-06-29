package com.capstone.maroonlabkom.data
import com.capstone.maroonlabkom.model.FakeMemberDataSource
import com.capstone.maroonlabkom.model.Member
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MemberRepository {
    private val memberData = mutableListOf<Member>()
    init {
        if(memberData.isEmpty()){
            FakeMemberDataSource.dummyMembers.forEach{
                memberData.add(it)
            }
        }
    }
    fun getAllMember():Flow<List<Member>>{
       return flowOf(memberData)
   }

    fun getMemberById(memberId: Int):Member{
        return memberData.first{
            it.id == memberId
        }
    }

    companion object{
        @Volatile
        private var instance:MemberRepository?=null
        fun getInstance():MemberRepository= instance?: synchronized(this){
            MemberRepository().apply {
                instance = this
            }
        }
    }
}