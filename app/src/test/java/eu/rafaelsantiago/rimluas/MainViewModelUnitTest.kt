package eu.rafaelsantiago.rimluas

import eu.rafaelsantiago.rimluas.domain.enum.LuasStopEnum
import eu.rafaelsantiago.rimluas.presentation.main.viewmodel.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalTime

class MainViewModelUnitTest {

    //region evaluateTime()
    @Test
    fun `Opening app at 00_00`() {
        // GIVEN
        val viewmodel = MainViewModel()
        val localTime = LocalTime.of(0, 0, 0)

        // WHEN
        val result = viewmodel.evaluateTime(localTime)

        // THEN
        val expected = LuasStopEnum.MARLBOROUGH
        assertEquals(expected, result)
    }

    @Test
    fun `Opening app at 12_00`() {
        // GIVEN
        val viewmodel = MainViewModel()
        val localTime = LocalTime.of( 12, 0, 0)

        // WHEN
        val result = viewmodel.evaluateTime(localTime)

        // THEN
        val expected = LuasStopEnum.MARLBOROUGH
        assertEquals(expected, result)
    }

    @Test
    fun `Opening app at 12_01`() {
        // GIVEN
        val viewmodel = MainViewModel()
        val localTime = LocalTime.of( 12, 1, 0)

        // WHEN
        val result = viewmodel.evaluateTime(localTime)

        // THEN
        val expected = LuasStopEnum.STILLORGAN
        assertEquals(expected, result)
    }

    @Test
    fun `Opening app at 23_59`() {
        // GIVEN
        val viewmodel = MainViewModel()
        val localTime = LocalTime.of(23, 59, 0)

        // WHEN
        val result = viewmodel.evaluateTime(localTime)

        // THEN
        val expected = LuasStopEnum.STILLORGAN
        assertEquals(expected, result)
    }

    @Test
    fun `Opening app at 03_00`() {
        // GIVEN
        val viewmodel = MainViewModel()
        val localTime = LocalTime.of(3, 0, 0)

        // WHEN
        val result = viewmodel.evaluateTime(localTime)

        // THEN
        val expected = LuasStopEnum.MARLBOROUGH
        assertEquals(expected, result)
    }

    @Test
    fun `Opening app at 14_00`() {
        // GIVEN
        val viewmodel = MainViewModel()
        val localTime = LocalTime.of(14, 0, 0)

        // WHEN
        val result = viewmodel.evaluateTime(localTime)

        // THEN
        val expected = LuasStopEnum.STILLORGAN
        assertEquals(expected, result)
    }
    //endregion

}