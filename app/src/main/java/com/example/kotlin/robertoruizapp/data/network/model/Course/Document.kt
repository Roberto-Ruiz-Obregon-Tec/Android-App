package com.example.kotlin.robertoruizapp.data.network.model.Course

/**
 * Class that represent a document associated to a Course.
 *
 * @property _id Unique identifier of a document.
 * @property cost Cost of a course.
 * @property capacity Number maximum of people in a course.
 * @property rating Rating of a course.
 * @property meetingCode Zoom meeting code to enter to a course.
 * @property accessCode Zoom access code to enter to a meeting.
 * @property name Name of a course.
 * @property description Description of a course.
 * @property speaker Name of the person who is going to present a course.
 * @property startDate Start date of a course.
 * @property endDate End date of a course.
 * @property schedule Schedule of a course.
 * @property modality If the course is remote or face-to-face.
 * @property postalCode Postal code of the place where the course will be given.
 * @property location Location of the place where the course will be given.
 * @property status If the course is still available or not.
 * @property courseImage Image related to a course.
 * @property focus List of labels related to a course.
 */
data class Document(
    val _id: String,
    val cost: Int,
    val capacity: Int,
    val rating: Int,
    val meetingCode: String,
    val accessCode: String,
    val name: String,
    val description: String,
    val speaker: String,
    val startDate: String,
    val endDate: String,
    val schedule: String,
    val modality: String,
    val postalCode: Int,
    val location: String,
    val status: String,
    val courseImage: String,
    val focus: List<String>
)