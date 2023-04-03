package com.test.testgraphqlkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.test.testgraphqlkotlin.databinding.ActivityMainBinding
import androidx.lifecycle.lifecycleScope
import com.test.LaunchListQuery
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.test.setOnClickListener {

            lifecycleScope.launch {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("https://apollo-fullstack-tutorial.herokuapp.com/graphql")
                    .build()

                //需要先创建LaunchList.graphql和schema.graphqls后，生成一下后，
                //通过LaunchList.graphql，LaunchListQuery.kt文件能够自动生成，LaunchListQuery()才能够运用
                val response = apolloClient.query(LaunchListQuery()).execute()
                Log.e("全部数据", "Success ${response.data}")

                //获取各数据
                val launches = response?.data?.launches?.launches?.filterNotNull()
                launches?.forEach {
                    Log.i("获取各数据", "launches $it")
                    Log.i("it.id", it.id)
                    it.site?.let { it1 -> Log.i("it.site", it1) }
                    it.mission?.name?.let { it2 -> Log.i("it.mission.name", it2) }
                    it.mission?.missionPatch?.let { it2 -> Log.i("it.mission.missionPatch", it2) }
                }
            }
        }

    }
}