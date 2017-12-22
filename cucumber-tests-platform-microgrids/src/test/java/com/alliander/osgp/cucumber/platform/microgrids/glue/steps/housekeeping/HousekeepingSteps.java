package com.alliander.osgp.cucumber.platform.microgrids.glue.steps.housekeeping;

import org.springframework.beans.factory.annotation.Autowired;

import com.alliander.osgp.cucumber.platform.microgrids.support.ResponseDataStateChecker;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HousekeepingSteps {

    @Autowired
    private ResponseDataStateChecker responseDataStateChecker;

    @When("^the microgrids response data cleanup job runs$")
    public void theRtuResponseDataCleanupJobRuns() {
        // Do nothing - Scheduled task runs automatically
    }

    @Then("^the record with correlation uid \"(.*)\" should be deleted$")
    public void theRecordShouldBeDeleted(final String correlationUid) {

        this.responseDataStateChecker.waitForRtuResponseDataToBeRemoved(correlationUid, 60000, 120000);
    }

    @Then("^the record with correlation uid \"(.*)\" should not be deleted$")
    public void theRecordShouldNotBeDeleted(final String correlationUid) {

        this.responseDataStateChecker.waitToMakeSureRtuResponseDataIsNotRemoved(correlationUid, 60000, 120000);
    }
}
