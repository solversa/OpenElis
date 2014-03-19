package org.bahmni.feed.openelis.feed.service.impl;

import org.bahmni.feed.openelis.externalreference.dao.ExternalReferenceDao;
import org.bahmni.feed.openelis.externalreference.valueholder.ExternalReference;
import org.bahmni.feed.openelis.feed.contract.bahmnireferencedata.ReferenceDataDepartment;
import org.bahmni.feed.openelis.feed.contract.bahmnireferencedata.ReferenceDataSample;
import org.bahmni.feed.openelis.feed.contract.bahmnireferencedata.ReferenceDataTest;
import org.bahmni.feed.openelis.utils.AuditingService;
import org.junit.Before;
import org.mockito.Mock;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.dao.TestSectionDAO;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleTestDAO;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

import java.io.IOException;
import java.util.Date;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TestServiceTest {

    @Mock
    private AuditingService auditingServiceMock;
    @Mock
    private TestDAO testDAOMock;
    @Mock
    private ExternalReferenceDao externalReferenceDaoMock;
    @Mock
    private TestSectionDAO testSectionDAOMock;
    @Mock
    private TypeOfSampleDAO typeOfSampleDAOMock;
    @Mock
    private TypeOfSampleTestDAO typeOfSampleTestDAOMock;

    private TestService testService;

    @Before
    public void setUp() {
        initMocks(this);

         testService = new TestService(externalReferenceDaoMock, testDAOMock, testSectionDAOMock,
                auditingServiceMock, typeOfSampleDAOMock, typeOfSampleTestDAOMock);

    }


    @org.junit.Test (expected=LIMSRuntimeException.class)
    public void failSavingATestIfTestSectionDoesNotExist() throws IOException {

        ReferenceDataTest referenceDataTest = createReferenceDataTestWithSomeDepartment("someDeptID");

        when(testSectionDAOMock.getTestSectionByUUID(anyString())).thenReturn(null);
        when(typeOfSampleDAOMock.getTypeOfSampleByUUID(anyString())).thenReturn(createDummyTypeOfSample());
        when(externalReferenceDaoMock.getData(referenceDataTest.getId(), TestService.CATEGORY_TEST)).thenReturn(createDummyReferenceData());
        when(testDAOMock.getTestById(anyString())).thenReturn(createDummyTest());

        testService.createOrUpdate(referenceDataTest);
    }


    @org.junit.Test(expected=LIMSRuntimeException.class)
    public void failSavingATestIfTestSectionIDPassedIsNull() throws IOException {

        ReferenceDataTest referenceDataTest = createReferenceDataTestWithSomeDepartment(null);

        when(testSectionDAOMock.getTestSectionByUUID(anyString())).thenReturn(null);
        when(typeOfSampleDAOMock.getTypeOfSampleByUUID(anyString())).thenReturn(createDummyTypeOfSample());
        when(externalReferenceDaoMock.getData(referenceDataTest.getId(), TestService.CATEGORY_TEST)).thenReturn(createDummyReferenceData());
        when(testDAOMock.getTestById(anyString())).thenReturn(createDummyTest());

        testService.createOrUpdate(referenceDataTest);
    }


    //------- Private methods --------------

    private ReferenceDataTest createReferenceDataTestWithSomeDepartment(String deptID) {
        ReferenceDataTest referenceDataTest = new ReferenceDataTest();
        referenceDataTest.setId("1");
        referenceDataTest.setDepartment(createDummyDepartment(deptID));
        referenceDataTest.setIsActive(Boolean.TRUE);
        referenceDataTest.setLastUpdated(new Date());
        referenceDataTest.setSample(createDummySample());

        return referenceDataTest;
    }

    private TypeOfSample createDummyTypeOfSample() {
        TypeOfSample typeOfSample = new TypeOfSample();
        typeOfSample.setId("dummyTypeOfSampleID");
        return typeOfSample;
    }

    private ExternalReference createDummyReferenceData() {
        ExternalReference externalReference = new ExternalReference();
        externalReference.setItemId(new Long("1"));
        return externalReference;
    }

    private Test createDummyTest() {
        Test test = new Test();
        test.setId("1");
        return test;
    }

    private ReferenceDataDepartment createDummyDepartment(String deptID) {
        ReferenceDataDepartment referenceDataDepartment = new ReferenceDataDepartment();
        referenceDataDepartment.setId(deptID);
        return referenceDataDepartment;
    }

    private ReferenceDataSample createDummySample() {
        ReferenceDataSample referenceDataSample = new ReferenceDataSample();
        referenceDataSample.setId("dummySampleID");
        return referenceDataSample;
    }
}