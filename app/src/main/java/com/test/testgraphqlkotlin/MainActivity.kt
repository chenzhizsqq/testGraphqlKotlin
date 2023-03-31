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
                Log.e("LaunchList", "Success ${response.data}")
            }
        }

    }
}