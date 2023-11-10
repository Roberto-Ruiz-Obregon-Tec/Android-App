package com.example.kotlin.robertoruizapp.data.network.model.Course

/**
 * Class that represent an object "Course".
 *
 * @property data List of documents of Course.
 * @property results Number of results related to Course.
 * @property status Course status.
 */
data class CourseObject(
    val `data`: List<Document>,
    val results: Int?,
    val status: String
)