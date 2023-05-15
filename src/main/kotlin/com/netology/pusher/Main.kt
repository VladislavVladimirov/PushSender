package com.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions.*
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream
import java.lang.NumberFormatException

fun main() {
    val options = builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    while (true) {
        println(
            "Выберите action:\n" +
                    "1.Like\n" +
                    "2.New post\n" +
                    "3.Unkown action\n" +
                    "4. Выход"
        )
        try {
            val input = Integer.parseInt(readLine())
            when (input) {
                1 -> {
                    val message = Message.builder()
                        .putData("action", "LIKE")
                        .putData(
                            "content", """{
          "userId": 1,
          "userName": "Владислав",
          "postId": 2,
          "postAuthor": "Нетология. Университет интернет-профессий"
        }""".trimIndent()
                        )
                        .setToken(token)
                        .build()
                    FirebaseMessaging.getInstance().send(message)
                }

                2 -> {
                    val message = Message.builder()
                        .putData("action", "NEW_POST")
                        .putData(
                            "content", """{
      "authorName" : "Владислав",
      "text" : "Kotlin — это язык программирования, созданный в компании JetBrains. Его разработали в 2011 году на замену Java, который в компании считали чересчур многословным. Новый язык получился на 40% компактнее предшественника, что помогло ускорить работу над основным продуктом JetBrains — средой разработки IntelliJ IDEA. При этом Kotlin полностью совместим с Java, потому что запускается на его виртуальной машине (JVM). "
        }""".trimIndent()
                        )
                        .setToken(token)
                        .build()
                    FirebaseMessaging.getInstance().send(message)
                }

                3 -> {
                    val message = Message.builder()
                        .putData("action", "UNKNOWN")
                        .putData(
                            "content", """{
          "userId": 1,
          "userName": "Владислав",
          "postId": 2,
          "postAuthor": "Нетология. Университет интернет-профессий"
        }""".trimIndent()
                        )
                        .setToken(token)
                        .build()
                    FirebaseMessaging.getInstance().send(message)
                }

                4 -> {
                    break
                }
            }
        } catch (e: NumberFormatException) {
            println("Введите число\n")
        }
    }
}