package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primaryMock;
  private TorpedoStore secondaryMock;


  @Before
  public void init(){
    primaryMock = mock(TorpedoStore.class);
    secondaryMock = mock(TorpedoStore.class);
    this.ship = new GT4500(primaryMock, secondaryMock);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primaryMock, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primaryMock, times(1)).fire(1);
    verify(secondaryMock, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_FirstEmpty(){
    // Arrange
    when(primaryMock.isEmpty()).thenReturn(true);
    when(secondaryMock.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primaryMock, times(0)).fire(1);
    verify(secondaryMock, times(2)).fire(1);
  }

  @Test
  public void fireTorpedo_SecondEmpty(){
// Arrange
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.isEmpty()).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primaryMock, times(2)).fire(1);
    verify(secondaryMock, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_BothEmpty(){
    // Arrange
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.isEmpty()).thenReturn(true);

    ship.fireTorpedo(FiringMode.SINGLE);

    when(primaryMock.isEmpty()).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primaryMock, times(1)).fire(1);
    verify(secondaryMock, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_FirstFailedSecondNotFired(){
    // Arrange
    when(primaryMock.fire(1)).thenReturn(false);
    when(secondaryMock.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primaryMock, times(1)).fire(1);
    verify(secondaryMock, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_SecondFailedFirstNotFired(){
    // Arrange
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.fire(1)).thenReturn(false);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primaryMock, times(1)).fire(1);
    verify(secondaryMock, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_FirstEmptyFiringAll(){
    // Arrange
    when(primaryMock.isEmpty()).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primaryMock, times(0)).fire(1);
    verify(secondaryMock, times(0)).fire(1);
  }


  @Test
  public void fireLaser_Test(){
// Arrange
    Boolean result = ship.fireLaser(FiringMode.SINGLE);

    assertEquals(false, result);
  }

}
