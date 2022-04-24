package ar.edu.unahur.obj2.semillas

open class Planta(var altura: Double, val anioSemilla: Int) {
    open val horasDeSolToleradas: Int = 9

    open fun esFuerte() = horasDeSolToleradas > 9

    open fun daSemillas()= false

    open fun espacio()= 0.0

    open fun esIdealLa(parcela: Parcela)= false

}
open class Menta(altura: Double, anioSemilla: Int): Planta(altura , anioSemilla) {

    override fun esIdealLa(parcela: Parcela)= parcela.superficieDeParcelas > 6

    override fun daSemillas()= espacio() >= 1.4

    override fun espacio()= altura+1
}

open class Soja(altura: Double, anioSemilla: Int): Planta(altura , anioSemilla)  {
    override var horasDeSolToleradas= horasDeSolToleradas()
    fun horasDeSolToleradas() =
        if (altura < 0.5) {
            6
        }
        else if(altura < 1){
            8
        }
        else {
            12
        }
    override fun esIdealLa(parcela: Parcela)= parcela.horasDeSolQueRecibe == horasDeSolToleradas

    override fun espacio()= altura/2

    override fun daSemillas()= anioSemilla > 2007 && altura > 0.75 && altura < 0.9

    override fun esFuerte() = horasDeSolToleradas > super.horasDeSolToleradas
}
class Quinoa (altura: Double, anioSemilla: Int): Planta(altura , anioSemilla) {
    override var horasDeSolToleradas= horasDeSolToleradas()
    fun horasDeSolToleradas() =
        if (espacio() < 0.3) {10}
        else {9}

    override fun esIdealLa(parcela: Parcela)= parcela.plantasEnParcela.all{ it.altura < 1.5}

    override fun esFuerte()= horasDeSolToleradas() >= 10

    override fun daSemillas()= anioSemilla in 2002..2008 || esFuerte()

    override fun espacio()= altura
}
class Peperina(altura: Double, anioSemilla: Int): Menta(altura , anioSemilla) {
    override fun espacio()= super.espacio() * 2
    override fun esIdealLa(parcela: Parcela)= parcela.superficieDeParcelas > 6
}
class SojaTransgenica(altura: Double, anioSemilla: Int): Soja(altura , anioSemilla) {
    override fun daSemillas()= false
    override fun esIdealLa(parcela: Parcela)= parcela.cantidadDePlantasQueTolera()== 1
}

