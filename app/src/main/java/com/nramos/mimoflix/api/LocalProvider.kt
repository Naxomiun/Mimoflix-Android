package com.nramos.mimoflix.api

import com.nramos.mimoflix.R
import com.nramos.mimoflix.model.localgenre.LocalGenre
import com.nramos.mimoflix.model.PopularPromoMovie

class LocalProvider {

    val getPopularPromoMovies : List<PopularPromoMovie> = listOf(
        PopularPromoMovie(
            64690,
            19.128,
             R.drawable.recommended_drive,
            "2011-08-06",
            7.5,
            "Drive",
            "Durante el día, Driver (Ryan Gosling) trabaja en un taller y es conductor especialista de cine, pero, algunas noches de forma esporádica, trabaja como chófer para delincuentes. Shannon (Brian Cranston), su mentor y jefe, que conoce bien su talento al volante, le busca directores de cine y televisión o criminales que necesiten al mejor conductor para sus fugas, llevándose la correspondiente comisión. Pero el mundo de Driver cambia el día en que conoce a Irene (Carey Mulligan), una guapa vecina que tiene un hijo pequeño y a su marido en la cárcel.\""
        ),
        PopularPromoMovie(
            76341,
            26.759,
             R.drawable.recommended_mad_max,
            "2015-05-13",
            7.5,
            "Mad Max",
            "Perseguido por su turbulento pasado, Mad Max cree que la mejor forma de sobrevivir es ir solo por el mundo. Sin embargo, se ve arrastrado a formar parte de un grupo que huye a través del desierto en un War Rig conducido por una Emperatriz de élite: Furiosa. Escapan de una Ciudadela tiranizada por Immortan Joe, a quien han arrebatado algo irreemplazable. Enfurecido, el Señor de la Guerra moviliza a todas sus bandas y persigue implacablemente a los rebeldes en una \"guerra de la carretera\" de altas revoluciones... Cuarta entrega de la saga post-apocalíptica que resucita la trilogía que a principios de los ochenta protagonizó Mel Gibson."

        ),
        PopularPromoMovie(
            155,
            48.902,
            R.drawable.recommended_batman,
            "2008-07-16",
            8.4,
            "Batman",
            "Batman/Bruce Wayne regresa para continuar su guerra contra el crimen. Con la ayuda del teniente Jim Gordon y del Fiscal del Distrito Harvey Dent, Batman se propone destruir el crimen organizado en la ciudad de Gotham. El triunvirato demuestra su eficacia, pero, de repente, aparece Joker, un nuevo criminal que desencadena el caos y tiene aterrados a los ciudadanos."
        ),
        PopularPromoMovie(
            11,
            70.414,
            R.drawable.recommended_star_wars,
            "1977-05-25",
            8.2,
            "Star Wars",
            "La princesa Leia, líder del movimiento rebelde que desea reinstaurar la República en la galaxia en los tiempos ominosos del Imperio, es capturada por las malévolas Fuerzas Imperiales, capitaneadas por el implacable Darth Vader, el sirviente más fiel del emperador. El intrépido Luke Skywalker, ayudado por Han Solo, capitán de la nave espacial \"El Halcón Milenario\", y los androides, R2D2 y C3PO, serán los encargados de luchar contra el enemigo y rescatar a la princesa para volver a instaurar la justicia en el seno de la Galaxia."
        ),
        PopularPromoMovie(
            1726,
            51.97,
            R.drawable.recommended_iron_man,
            "2008-04-30",
            7.6,
            "Iron Man",
            "El multimillonario fabricante de armas Tony Stark debe enfrentarse a su turbio pasado después de sufrir un accidente con una de sus armas. Equipado con una armadura de última generación tecnológica, se convierte en \"El hombre de hierro\" para combatir el mal a escala global."
        )
    )

    val getLocalGenres : List<LocalGenre> = listOf(
        LocalGenre(
            28,
            R.string.action,
            R.color.genre_action,
            R.drawable.gaction
        ),
        LocalGenre(
            16,
            R.string.animation,
            R.color.genre_animated,
            R.drawable.ganimated
        ),
        LocalGenre(
            35,
            R.string.comedy,
            R.color.genre_comedy,
            R.drawable.gcomedy
        ),
        LocalGenre(
            18,
            R.string.drama,
            R.color.genre_drama,
            R.drawable.gdrama
        ),
        LocalGenre(
            80,
            R.string.crime,
            R.color.genre_crime,
            R.drawable.gcrime
        ),
        LocalGenre(
            14,
            R.string.fantasy,
            R.color.genre_fantasy,
            R.drawable.gfantasy
        ),
        LocalGenre(
            27,
            R.string.horror,
            R.color.genre_horror,
            R.drawable.ghorror
        ),
        LocalGenre(
            878,
            R.string.sfiction,
            R.color.genre_sfiction,
            R.drawable.gsfiction
        ),
        LocalGenre(
            10752,
            R.string.war,
            R.color.genre_war,
            R.drawable.gwar
        ),
        LocalGenre(
            37,
            R.string.wastern,
            R.color.genre_western,
            R.drawable.gwestern
        )
    )

}