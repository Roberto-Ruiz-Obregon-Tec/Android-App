package com.example.kotlin.robertoruizapp.data.network.model.commentCourse

data class Data(
    val documents: List<Document>
) {
    override fun toString(): String {
        return "Data(data=$documents)"
    }
}
