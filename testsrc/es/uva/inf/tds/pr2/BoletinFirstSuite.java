package es.uva.inf.tds.pr2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;

@RunWith(JUnitPlatform.class)
@SelectPackages("es.uva.inf.tds.tdd.money")
@SuiteClasses({ BoletinTDDFixtureSimple.class, BoletinTDDFixtureVarious.class, BoletinBlackBoxSimple.class, BoletinBlackBoxVarious.class })
@IncludeTags({ "Positive|Negative" })
@ExcludeTags({ "BlackBoxTestFirst" })

public class BoletinFirstSuite {

}