package com.example.domain.models

import java.io.Serializable

data class ShowModel(
    val links: LinksModel,
    val averageRuntime: Int,
    val ended: String,
    val externals: ExternalsModel,
    val genres: List<String>,
    val id: Int,
    val image: ImageModel,
    val language: String,
    val name: String,
    val network: NetworkModel,
    val officialSite: String,
    val premiered: String,
    val rating: RatingModel,
    val runtime: Int,
    val schedule: ScheduleModel,
    val status: String,
    val summary: String,
    val type: String,
    val updated: Int,
    val url: String,
    val weight: Int,
    val isFavorite: Boolean = false,
) : Serializable {

    val year: String
        get() = if (premiered.length >= 4) premiered.substring(0, 4) else "Unknown"

    companion object {
        val EMPTY = ShowModel(
            links = LinksModel.EMPTY,
            averageRuntime = 0,
            ended = "",
            externals = ExternalsModel.EMPTY,
            genres = emptyList(),
            id = 0,
            image = ImageModel.EMPTY,
            language = "",
            name = "",
            network = NetworkModel.EMPTY,
            officialSite = "",
            premiered = "",
            rating = RatingModel.EMPTY,
            runtime = 0,
            schedule = ScheduleModel.EMPTY,
            status = "",
            summary = "",
            type = "",
            updated = 0,
            url = "",
            weight = 0,
        )

        val MOCK = ShowModel(
            links = LinksModel.MOCK,
            averageRuntime = 30,
            ended = "",
            externals = ExternalsModel.MOCK,
            genres = listOf(
                "Comedy",
                "Family",
            ),
            id = 0,
            image = ImageModel.MOCK,
            language = "",
            name = "Girl Meets World",
            network = NetworkModel.MOCK,
            officialSite = "",
            premiered = "2014-06-27",
            rating = RatingModel.MOCK,
            runtime = 0,
            schedule = ScheduleModel.MOCK,
            status = "",
            summary = "<p>In a very imaginable future, the world battles terrorism and unrest. Out of this chaos emerges a new hero: Jane Vasco, a.k.a. Painkiller Jane. Once the DEA's top agent, Jane Vasco is formidable, both mentally and physically. As a child, her father nicknamed her Painkiller Jane, describing her ability to mentally push through even the most painful situations. But her strength is about to be tested. Jane is recruited by a covert government agency dedicated to containing and, if necessary, neutralizing the threat of \\\"Neuros\\\" — individuals with superhuman neurological powers. No one knows what caused the aberrations that led to their enhanced abilities, which range from from telekinesis and telepathic suggestion to induced hallucinations. During her first investigation with her new team, Jane discovers that she too possesses an odd ability: she can't be killed. Unfortunately, she can still feel pain. Her newfound powers make Jane even more determined to learn everything there is to know about Neuros. Seldom malicious, Neuros often can't control their powers. Consequently, they tend to leave a trail of death and destruction. To prevent a panic, the government has kept the discovery of Neuros a secret, assembling a covert unit to identify and contain Neuros. Operating from a secure abandoned subway platform, the core members of the unit are Andre McBride, the seasoned team leader; Connor King, a special agent regularly armed with a smart remark; Riley Jensen, an evolved computer whiz in charge of surveillance and communications; Dr. Seth Carpenter, the unit's doctor and scientist; Joe Waterman, the middle-aged caretaker of the subway; and Maureen Bowers, Jane's former DEA partner and friend, who, like Jane, was recruited after discovering top-secret information about Neuros. Painkiller Jane is based on the comic-book series of the same name created by Jimmy Palmiotti and Joe Quesada.</p>",
            type = "",
            updated = 0,
            url = "",
            weight = 0,
        )
    }
}
