Feature: automating fourmodules test scenarios 

@Regression1 @verifyFourModuleMenuOptions 
Scenario: Print fourmodules menu options 
	Given user navigate to with "fourmoduleAdmin" role 
	When I mousehover on "CD_IELTS_Menu_Link" dropdown in "FourModules_Menu" page 
	Then Print the dropdown options displayed 
	And I mousehover on "PTE_Menu_Link" dropdown in "FourModules_Menu" page 
	And Print the dropdown options displayed 
	And I mousehover on "CELPIP_Menu_Link" dropdown in "FourModules_Menu" page 
	And Print the dropdown options displayed 
	And I mousehover on "CAEL_Menu_Link" dropdown in "FourModules_Menu" page 
	And Print the dropdown options displayed 
	
	
@Regression1 @closeWindowsPopup 
Scenario: Close popup window 
	Given user navigate to with "fourmoduleAdmin" role 
	When I mousehover on "CD_IELTS_Menu_Link" dropdown in "FourModules_Menu" page 
	Then Print the dropdown options displayed 
	And I click on option About 
	And I verify current url after clicking about 
	And I see a button "AnyQuestions_button" on "FourModules_Menu" page 
	And I click this "AnyQuestions_button" button on "FourModules_Menu" page 
	And I see a pop window with Title "How_can_we_help" on "FourModules_Menu" page 
	And I press close "close_icon" on this pop window on "FourModules_Menu" page 
	And I click on "Take_Demo" button on "FourModules_Menu" page 
	And I see current url as signup 
	
	
@Regression1 @verifySpiceJetDefaultMenuOptions 
Scenario: Print fourmodules menu options 
	Given user navigate to with "spicejetAdmin" role 
	When I see Navbar that includes the "SpiceJet_Menu" on "SpiceJet_Page" page 
	Then I see "SpiceJet_Menu" is selected by default on "SpiceJet_Page" page 
	And I see clickable radio buttons "radio_buttons" under Flights option on "SpiceJet_Page" page 
	
	
@Regression1 @verifySpiceJetFlightsDefault 
Scenario: Print fourmodules menu options 
	Given user navigate to with "spicejetAdmin" role 
	When I select 1Adult_textbox and "4Infants_textbox" on "SpiceJet_Page" page 
	Then I see a pop-up window with a message 
	And Record the message and print it to the Console 
	
	
	
@Regression1 @verifySpiceJetBook2Adults 
Scenario: Print fourmodules menu options 
	Given user navigate to with "spicejetAdmin" role 
	When I select "OneWay_radio_button" radio button on "SpiceJet_Page" page 
	And I select "Book_menu_header" tab on "SpiceJet_Page" page 
	And I select "Flights_sub_menu" on "SpiceJet_Page" page 
	And I select oneway radio "OneWay_radio_button" on "SpiceJet_Page" page 
	And I select Agra_textbox departure city in the "From_textbox" field on "SpiceJet_Page" page 
	And I select Jaipur_textbox arrival city in the "To_textbox" field on "SpiceJet_Page" page 
	And I select any date in the "DepartDate_textbox" field on "SpiceJet_Page" page 
	And I select "Passengers_date" from 2Adults dropdown on "SpiceJet_Page" page 
	And I select "Currency_USD" from the Currency dropdown on "SpiceJet_Page" page 
	And I click on "Search_icon" CTA button on "SpiceJet_Page" page 
	Then Redirect me to book_url 
	And Verify the user has advanced to the next page by printing to Console the URL 
	
	
	
@Regression1 @verifySpiceJetBook1Adult 
Scenario: Print fourmodules menu options 
	Given user navigate to with "spicejetAdmin" role 
	When I select "Book_menu_header" tab on "SpiceJet_Page" page 
	And I select "Flights_sub_menu" on "SpiceJet_Page" page 
	And I select oneway radio "OneWay_radio_button" on "SpiceJet_Page" page 
	And I select Agra_textbox departure city in the "From_textbox" field on "SpiceJet_Page" page 
	And I select Jaipur_textbox arrival city in the "To_textbox" field on "SpiceJet_Page" page 
	And I select any date in the "DepartDate_textbox" field on "SpiceJet_Page" page 
	And I select "Passengers_date" from 1Adults dropdown on "SpiceJet_Page" page 
	And I select "Currency_USD" from the Currency dropdown on "SpiceJet_Page" page 
	And I checkbox "Family_And_Friends_checkbox" on "SpiceJet_Page" page 
	And I click on "Search_icon" CTA button on "SpiceJet_Page" page 
	And I see a pop-up window with a message 
	
	
@Regression1 @verifySpiceJetSearch 
Scenario: Print fourmodules menu options 
	Given user navigate to with "spicejetAdmin" role 
	When I select "Book_menu_header" tab on "SpiceJet_Page" page 
	And I select "Flights_sub_menu" on "SpiceJet_Page" page 
	And I select oneway radio "OneWay_radio_button" on "SpiceJet_Page" page 
	And I select Agra_textbox departure city in the "From_textbox" field on "SpiceJet_Page" page 
	And I select Jaipur_textbox arrival city in the "To_textbox" field on "SpiceJet_Page" page 
	And I select any date in the "DepartDate_textbox" field on "SpiceJet_Page" page 
	And I select "Passengers_date" from 1Adults dropdown on "SpiceJet_Page" page 
	And I select "Currency_USD" from the Currency dropdown on "SpiceJet_Page" page 
	And I click on "Search_icon" CTA button on "SpiceJet_Page" page 
	And Clicking on "Search_icon" CTA button will redirect me to url on "SpiceJet_Page" page 
	Then If no fares are available, record the message and print to the Console 
	And I click on modifysearch "Modify_Search" button on "SpiceJet_Page" page
	And I see the "Go_CTA_button" CTA button on "SpiceJet_Page" page
	And I click "Go_CTA_button" CTA button on "SpiceJet_Page" page
	And I see the page interstitial 
	
