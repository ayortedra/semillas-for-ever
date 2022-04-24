package ar.edu.unahur.obj2.semillas

open class Parcela(var ancho:Int, var largo:Int, var horasDeSolQueRecibe:Int) {

  var plantasEnParcela= mutableListOf<Planta>()

  var superficieDeParcelas = ancho*largo

  fun cantidadDePlantasQueTolera() =
    if (ancho > largo){ superficieDeParcelas/5 }
    else{(superficieDeParcelas/3)+largo}

  //Sí si alguna de sus plantas tolera menos sol del que recibe la parcela;
  fun tieneComplicaciones()=
    plantasEnParcela.any{ it.horasDeSolToleradas <= horasDeSolQueRecibe }

  fun plantarLa_(planta:Planta) {
    if(cantidadDePlantasQueTolera() > plantasEnParcela.size && planta.horasDeSolToleradas+2 >= horasDeSolQueRecibe ) {
      plantasEnParcela.add(planta)
    }
    else{
      println("Error no se puede agregar Planta, por no haber espacio o no cumple con las especificaciones")
    }
  }
  open fun seAsociaA_(planta : Planta) = false

}
class ParcelaEcologica(ancho : Int, largo : Int, horasDeSolQueRecibe : Int) : Parcela(ancho, largo ,horasDeSolQueRecibe) {
  override  fun seAsociaA_(planta : Planta) = this.tieneComplicaciones() && planta.esIdealLa(this)

}

class ParcelaIndustrial(ancho : Int, largo : Int, horasDeSolQueRecibe : Int) : Parcela(ancho, largo, horasDeSolQueRecibe) {
   override fun seAsociaA_(planta:Planta) = (plantasEnParcela.size <= 2) and (planta.esFuerte())

}