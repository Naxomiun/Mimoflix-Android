package com.nramos.mimoflix.api

import com.nramos.mimoflix.R
import com.nramos.mimoflix.model.PopularPromoMovie
import com.nramos.mimoflix.model.Serie

class RecommendedProvider {

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

    val getRecommendedSeries : List<Serie> = listOf(
        Serie(
            2316,
            43.671,
            R.drawable.recommended_the_office,
            "2011-08-06",
            8.3,
            "The Office",
            "Steve Carell protagoniza The Office, un fresco y divertido vistazo, con formado pseudo-documental, al día a día en la vida de unos excéntricos trabajadores de la empresa Dunder Mifflin. El serio pero despistadísimo director Michael Scott se considera un magnífico jefe y mentor, pero realmente inspira más críticas que respeto a sus empleados."
        ),
        Serie(
            1396,
            99.656,
            R.drawable.recommended_breaking_bad,
            "2008-01-20",
            8.5,
            "Breaking Bad",
            "Tras cumplir 50 años, Walter White (Bryan Cranston), un profesor de química de un instituto de Albuquerque, Nuevo México, se entera de que tiene un cáncer de pulmón incurable. Casado con Skyler (Anna Gunn) y con un hijo discapacitado (RJ Mitte), la brutal noticia lo impulsa a dar un drástico cambio a su vida: decide, con la ayuda de un antiguo alumno (Aaron Paul), fabricar anfetaminas y ponerlas a la venta. Lo que pretende es liberar a su familia de problemas económicos cuando se produzca el fatal desenlace."
        ),
        Serie(
            67178,
            25.11,
            R.drawable.recommended_the_punisher,
            "2017-11-17",
            8.1,
            "The Punisher",
            "Un antiguo marine decidido a castigar a los asesinos de su familia termina atrapado en una conspiración militar."
        ),
        Serie(
            1399,
            80.051,
            R.drawable.recommended_game_of_thrones,
            "2011-04-17",
            8.2,
            "Game of Thrones",
            "Juego de Tronos es una serie de televisión de drama y fantasía creada para la HBO por David Benioff y D. B. Weiss. Es una adaptación de la saga de novelas de fantasía Canción de Hielo y Fuego de George R. R. Martin. La primera de las novelas es la que da nombre a la serie.\n\nLa serie, ambientada en los continentes ficticios de Westeros y Essos al final de un verano de una decada de duración, entrelaza varias líneas argumentales. La primera sigue a los miembros de varias casas nobles inmersos en una guerra civil por conseguir el Trono de Hierro de los Siete Reinos. La segunda trata sobre la creciente amenaza de un inminente invierno y sobre las temibles criaturas del norte. La tercera relata los esfuerzos por reclamar el trono de los últimos miembros exiliados de una dinastía destronada. A pesar de sus personajes moralmente ambiguos, la serie profundiza en los problemas de la jerarquía social, religión, lealtad, corrupción, sexo, guerra civil, crimen y castigo."
        ),
        Serie(
            82856,
            48.739,
            R.drawable.recommended_the_mandalorian,
            "2019-11-12",
            8.3,
            "The Mandalorian",
            "Tras las historias de Jango y Boba Fett, otro guerrero emerge en el universo de Star Wars. 'The Mandalorian' se sitúa después de la caída del Imperio y antes de la aparición de la Primera Orden. Seguimos las tribulaciones de un pistolero solitario en los confines de la galaxia, lejos de la autoridad de la Nueva República..."
        )
    )

}