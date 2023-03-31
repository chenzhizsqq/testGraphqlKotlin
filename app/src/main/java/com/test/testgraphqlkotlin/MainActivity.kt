package com.test.testgraphqlkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.test.testgraphqlkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.test.setOnClickListener {
            val apolloClient = ApolloClient.Builder()
                .serverUrl("https://apollo-fullstack-tutorial.herokuapp.com/graphql")
                .build()

            //val response = apolloClient.query(LaunchListQuery()).execute()
            //Log.d("LaunchList", "Success ${response.data}")
        }

    }
}