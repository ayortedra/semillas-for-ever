package ar.edu.unahur.obj2.semillas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class ParcelaTest: DescribeSpec( {
  describe("Creation de parcelas "){


    it("Cada planta define ciertas condiciones para saber si una parcela le resulta ideal"){
      val parcela= Parcela(20, 1, 10)
      val parcela1= Parcela(5, 1, 10)
      val soja = Soja(0.3, 2009)
      val sojaMedia = Soja(0.8, 2009)
      val sojaAlta = Soja(1.0, 2009)
      val sojaT = SojaTransgenica(1.6, 2006)
      val menta = Menta(1.0, 2021)
      val quinoata = Quinoa(0.9, 2006)
      val quinoa = Quinoa(0.2, 2010)

      parcela.plantarLa_(sojaAlta)
      parcela.plantarLa_(sojaAlta)
      parcela.plantarLa_(sojaAlta)
      parcela.plantarLa_(sojaAlta)


      parcela1.plantarLa_(sojaT)

      menta.esIdealLa(parcela).shouldBeTrue()

      quinoa.esIdealLa(parcela).shouldBeTrue()
      quinoata.esIdealLa(parcela1).shouldBeFalse()

      soja.esIdealLa(parcela).shouldBeFalse()

      sojaT.esIdealLa(parcela1).shouldBeTrue()
      sojaT.esIdealLa(parcela).shouldBeFalse()

    }

  }

})
class ParcelasTest : DescribeSpec({
  describe("La cantidad máxima de plantas que tolera una parcela"){
    val soja = Soja(0.6, 2009)
    val sojaAlta = Soja(1.0, 2009)
    val parcela= Parcela(20, 1, 10)
    val parcela1= Parcela(20, 1, 10)

    parcela.plantarLa_(sojaAlta)
    parcela.plantarLa_(sojaAlta)
    parcela.plantarLa_(sojaAlta)
    parcela.plantarLa_(sojaAlta)

    parcela1.plantarLa_(soja)

    parcela.superficieDeParcelas.shouldBe(20)

    parcela.cantidadDePlantasQueTolera().shouldBe(4)
    parcela1.cantidadDePlantasQueTolera().shouldBe(4)

    it("si alguna de sus plantas tolera menos sol del que recibe la parcela"){
      parcela.tieneComplicaciones().shouldBeFalse()
      parcela1.tieneComplicaciones().shouldBeTrue()

    }
    it("plantamos 4 plantas de soja de más de 1 metro y toleran 12 horas de sol") {
      parcela.plantasEnParcela.size.shouldBe(4)
      parcela1.plantasEnParcela.size.shouldBe(1)
      parcela.plantarLa_(soja)
      parcela.plantasEnParcela.size.shouldBe(4)
    }

  }
})

class ParcelaIdealTest: DescribeSpec ({
  describe ("Parcela ideal para x planta"){
    val parce1 = Parcela (20, 1, 10)
    val parce2 = Parcela (10, 5, 15)
    val parce3 = Parcela (5, 1, 8)

    val menta = Menta (1.0, 2020)
    val soja = Soja (0.6, 2010)
    val quinoa = Quinoa (0.2,2010)
    val peperina = Peperina (1.0,2021)
    val sojaTransgenica = SojaTransgenica (1.0, 2021)
    val sojaAlta = Soja(1.0, 2009)

    parce1.plantarLa_(sojaAlta)
    parce1.plantarLa_(sojaAlta)
    parce1.plantarLa_(sojaAlta)
    parce1.plantarLa_(menta)

    parce3.plantarLa_(soja)
    parce2.plantarLa_(soja)

    it("Parcela ideal para Menta") {
      menta.esIdealLa(parce1).shouldBeTrue()
      menta.esIdealLa(parce2).shouldBeTrue()
      menta.esIdealLa(parce3).shouldBeFalse()
    }

    it("Parcela ideal para Soja") {
      soja.esIdealLa(parce1).shouldBeFalse()
      soja.esIdealLa(parce2).shouldBeFalse()
      soja.esIdealLa(parce3).shouldBeTrue()
    }

    it("Parcela ideal para Quinoa") {
      quinoa.esIdealLa(parce1).shouldBeTrue()
      quinoa.esIdealLa(parce2).shouldBeTrue()
      quinoa.esIdealLa(parce3).shouldBeTrue()
    }

    it("Parcela ideal para Peperina") {
      peperina.esIdealLa(parce1).shouldBeTrue()
      peperina.esIdealLa(parce2).shouldBeTrue()
      peperina.esIdealLa(parce3).shouldBeFalse()
    }
    it("Parcela ideal para Soja Transgenica") {
      sojaTransgenica.esIdealLa(parce1).shouldBeFalse()
      sojaTransgenica.esIdealLa(parce2).shouldBeFalse()
      sojaTransgenica.esIdealLa(parce3).shouldBeTrue()
    }

  }


})
