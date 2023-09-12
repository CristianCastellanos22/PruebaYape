package com.cristian.pruebayape

import android.content.Context
import androidx.annotation.RawRes
import androidx.test.platform.app.InstrumentationRegistry
import com.cristian.pruebayape.di.NetworkModule
import com.cristian.pruebayape.utils.rawResToString
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import org.junit.After
import org.junit.Before

open class BaseUITest {

    /**
     * For MockWebServer instance
     */
    var mockServer: MockWebServer = MockWebServer()

    @Before
    open fun setUp() {
        startMockServer()
    }

    /**
     * Helps to read input file returns the respective data in mocked call
     */
    fun mockNetworkResponseWithFileContent(
        @RawRes rawFile: Int? = null,
        responseCode: Int,
        socketPolicy: SocketPolicy
    ) {
        val mockResponse = MockResponse()
            .setSocketPolicy(socketPolicy)
            .setResponseCode(responseCode)
        rawFile?.let { mockResponse.setBody(getContextTestInstrumentation().rawResToString(it)) }
        mockServer.enqueue(mockResponse)
    }

    /**
     * Reads input file and converts to json
     */
    private fun getContextTestInstrumentation(): Context =
        InstrumentationRegistry.getInstrumentation().context

    private fun startMockServer() {
        mockServer.start(8080)
        NetworkModule.BASE_URL = getMockWebServerUrl()
    }

    private fun getMockWebServerUrl() = mockServer.url("/").toString()

    private fun stopMockServer() {
        mockServer.shutdown()
    }

    @After
    open fun tearDown() {
        stopMockServer()
    }
}