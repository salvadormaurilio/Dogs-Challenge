package com.example.dogschallenge.ui

import com.example.dogschallenge.domain.model.Dog

fun getDogsTestData() = listOf(
    getDog1TestData(),
    getDog2TestData(),
    getDog3TestData(),
    getDog4TestData(),
    getDog5TestData()
)

fun getDog1TestData() = Dog(
    name = "Rex",
    description = "He is much more passive and is the first to suggest to rescue and not eat The Little Pilot",
    age = 5,
    image = "https://static.wikia.nocookie.net/isle-of-dogs/images/a/af/Rex.jpg/revision/latest/scale-to-width-down/666?cb=20180625001634"
)

private fun getDog2TestData() = Dog(
    name = "Spots",
    description = "Is the brother of Chief and are also a former guard dog for Mayor Kobayashi's ward",
    age = 3,
    image = "https://static.wikia.nocookie.net/isle-of-dogs/images/6/6b/Spots.jpg/revision/latest/scale-to-width-down/666?cb=20180624191101"
)

private fun getDog3TestData() = Dog(
    name = "Chief",
    description = "He is a leader of a pack of dogs",
    age = 8,
    image = "https://static.wikia.nocookie.net/isle-of-dogs/images/1/1d/Chief-0.jpg/revision/latest/scale-to-width-down/666?cb=20180624184431"
)

private fun getDog4TestData() = Dog(
    name = "Boss",
    description = "Little is known about Boss' origins other than he was the mascot for the Megasaki Dragons",
    age = 4,
    image = "https://static.wikia.nocookie.net/isle-of-dogs/images/6/6f/Boss.jpg/revision/latest/scale-to-width-down/666?cb=20180624190948"
)

private fun getDog5TestData() = Dog(
    name = "Spots",
    description = "Is the brother of Chief and are also a former guard dog for Mayor Kobayashi's ward",
    age = 2,
    image = "https://static.wikia.nocookie.net/isle-of-dogs/images/6/6b/Spots.jpg/revision/latest/scale-to-width-down/666?cb=20180624191101"
)

