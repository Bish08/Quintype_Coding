package com.fuber2.fuber2;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.dropwizard.testing.junit.ResourceTestRule;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.füber.Taxi;
import com.füber.TaxiDao;
import com.füber.TaxiResource;

public class RegisterTaxiResourceTest {
	private static final TaxiDao dao = mock(TaxiDao.class);
	
    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new TaxiResource(dao))
            .build();

    private final Taxi taxi = new Taxi("blah",20,30,true,false,true);

    @Before
    public void setup() {

        when(dao.registerTaxi("blah",10,20,true)).thenReturn(taxi);
        reset(dao);
    }

    @Test
    public void testRegisterTaxi() {
        assertThat(resources.client().resource("/fuber/taxi/register/blah/20/30/true").get(Taxi.class))
                .isEqualTo(taxi);
        verify(dao).registerTaxi("blah",10,20,true);
    }

}
