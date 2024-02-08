package com.adia.dev.playground.models

data class Manga(
    val id: String,
    val title: String,
    val author: String,
    val description: String,
    val cover: String,
    val status: String,
    val genres: List<String> = emptyList()
) {
    companion object {
        val EMPTY = Manga("", "", "", "", "", "", emptyList())
        val DUMMY_LIST = listOf(
            Manga(
                "1",
                "One Piece",
                "Eiichiro Oda",
                "One Piece is a Japanese manga series written and illustrated by Eiichiro Oda. It has been serialized in Shueisha's Weekly Shōnen Jump magazine since July 22, 1997, and has been collected into 98 tankōbon volumes as of February 2021. The story follows the adventures of Monkey D. Luffy",
                "https://m.media-amazon.com/images/I/51z5W9Z6jHL.jpg",
                "Ongoing",
                listOf("Action", "Adventure", "Comedy", "Drama", "Fantasy", "Shounen"),
            ),
            Manga(
                "2",
                "Naruto",
                "Masashi Kishimoto",
                "Naruto is a Japanese manga series written and illustrated by Masashi Kishimoto. It tells the story of Naruto Uzumaki, a young ninja who seeks recognition from his peers and dreams of becoming the Hokage, the leader of his village.",
                "https://m.media-amazon.com/images/I/51z5W9Z6jHL.jpg",
                "Completed",
                listOf("Action", "Adventure", "Comedy", "Drama", "Fantasy", "Shounen"),
            ),
            Manga(
                "3",
                "Bleach",
                "Tite Kubo",
                "Bleach is a Japanese manga series written and illustrated by Tite Kubo. Bleach follows the adventures of the hotheaded teenager Ichigo Kurosaki, who inherits his parents' destiny after he obtains the powers of a Soul Reaper",
                "https://m.media-amazon.com/images/I/51z5W9Z6jHL.jpg",
                "Completed",
                listOf("Action", "Adventure", "Comedy", "Drama", "Fantasy", "Shounen"),
            ),
        )
    }
}