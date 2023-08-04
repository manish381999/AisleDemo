package com.tie.aisledemo.ModelClass

class UserNotesResponse {
    private val invites: Invites? = null
    private val likes: Likes? = null

    // Other fields and their respective getters and setters
    inner class Invites {
        // Getters and setters
        var profiles: List<UserInformation>? = null
        var totalPages = 0
        var pending_invitations_count = 0
    }

    inner class Likes {
        // Getters and setters
        var profiles: List<UserInformation>? = null
        var isCan_see_profile = false
        var likes_received_count = 0
    }

    inner class UserInformation {
        // Other fields and their respective getters and setters
        var general_information: GeneralInformation? = null
        var approved_time: Long = 0
        var disapproved_time: Long = 0
        var photos: List<Photo>? = null
        var preferences: List<Preference>? = null
    }

    inner class GeneralInformation {
        // Other fields and their respective getters and setters
        var date_of_birth: String? = null
        var date_of_birth_v1: String? = null
        var location: Location? = null
        var first_name: String? = null
        var gender: String? = null
        var age = 0
    }

    inner class Location {
        // Getters and setters
        var summary: String? = null
        var full: String? = null
    }

    inner class Photo {
        // Getters and setters
        var photo: String? = null
        var photo_id = 0
        var isSelected = false
        var status: String? = null
    }

    inner class Preference {
        // Getters and setters
        var answer_id = 0
        var id = 0
        var value = 0
        var preference_question: PreferenceQuestion? = null
    }

    inner class PreferenceQuestion {
        // Getters and setters
        var first_choice: String? = null
        var second_choice: String? = null
    }

    inner class Industry {
        // Getters and setters
        var id = 0
        var name: String? = null
        var isPreference_only = false
    }

    inner class Experience {
        // Getters and setters
        var id = 0
        var name: String? = null
        var name_alias: String? = null
    }

    inner class HighestQualification {
        // Getters and setters
        var id = 0
        var name: String? = null
        var isPreference_only = false
    }

    inner class FieldOfStudy {
        var id = 0
        var name: String? = null
    }
}