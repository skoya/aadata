import org.apache.spark.sql.types._
import scala.collection.mutable.ArrayBuffer


def newColumn(name:String, dataType:DataType = StringType, nullable: Boolean = true, description:String): StructField = {
    val s = StructField(name, dataType, nullable, new MetadataBuilder().putString("Description", description).build())
    s
}

def newDefaultColumn(name: String, description: String): StructField = {
    val s = newColumn(name, StringType, true, description)
    s
}



def buildAdobeSchema(): ArrayBuffer[StructField] = {
    val sa = ArrayBuffer[StructField]()

    sa += newDefaultColumn("corrupted", "Errors when trying to convert to schema")
    sa += newDefaultColumn("accept_language", "Lists all accepted languages, as indicated in the Accept-Language HTTP header in an image request.")
    sa += newDefaultColumn("adclassificationcreative", "Creative ID")
    sa += newDefaultColumn("adload", "Media ad loads")
    sa += newDefaultColumn("aemassetid","A multi-value variable corresponding to Asset GUIDs of a set of Adobe Experience Manager Assets. Increments Impression Events.")
    sa += newDefaultColumn("aemassetsource","Identifies the source of the asset event. Used in Adobe Experience Manager.")
    sa += newDefaultColumn("aemclickedassetid","Asset ID of an Adobe Experience Manager asset. Increments Click Events.")
    sa += newColumn("browser", IntegerType, true, "Numeric ID of the browser. References the browser.tsv lookup table.")
    sa += newColumn("browser_height", IntegerType, true, "Height in pixels of the browser window.")
    sa += newColumn("browser_width", IntegerType, true, "Width in pixels of the browser window.")
    sa += newDefaultColumn("c_color","Bit depth of the color palette. Used as part of calculating the Color depth dimension. AppMeasurement uses the JavaScript function screen.colorDepth()")
    sa += newDefaultColumn("campaign","Variable used in the Tracking Code dimension.")
    sa += newDefaultColumn("carrier","Adobe Advertising Cloud integration variable. Specifies the mobile carrier. References the carrier lookup table.")
    sa += newDefaultColumn("ch_hdr", "Client hints collected through the HTTP request header.")
    sa += newDefaultColumn("ch_js", "Client hints collected through the User-Agent Client Hints JavaScript")
    sa += newDefaultColumn("channel","Variable used in the Site sections dimension.")
    sa += newDefaultColumn("click_action", "No longer used. Address of linked clicked in the legacy Clickmap tool.")
    sa += newColumn("click_action_type", IntegerType, true, "No longer used. Link type of the legacy Clickmap tool. 0: HREF URL 1: Custom ID 2: JavaScript onClick event 3: Form element")
    sa += newDefaultColumn("click_context", "No longer used. Page name where the link click occurred. Part of the legacy Clickmap tool.")
    sa += newColumn("click_context_type", IntegerType, true, "No longer used. Indicates if click_context had a page name or defaulted to page URL. 0: Page URL 1: Page Name")
    sa += newColumn("click_sourceid", IntegerType, true, "No longer used. Numeric ID for the location on the page of the clicked link. Part of the legacy Clickmap tool.")
    sa += newDefaultColumn("click_tag", "No longer used. Type of HTML element that was clicked.")
    sa += newDefaultColumn("clickmaplink","Activity Map link")
    sa += newDefaultColumn("clickmaplinkbyregion", "Activity Map link by region")
    sa += newDefaultColumn("clickmappage","Activity Map page")
    sa += newDefaultColumn("clickmapregion", "Activity Map region")
    sa += newDefaultColumn("code_ver","AppMeasurement Library version used to compile and send the image request.")
    sa += newColumn("color", IntegerType, true, "Color depth ID based on the value of the c_color column. References the color_depth.tsv lookup table.")
    sa += newColumn("connection_type", IntegerType, true, "Numeric ID representing the connection type. Variable used in the Connection type dimension. References the connection_type.tsv lookup table.")
    sa += newDefaultColumn("cookies","Variable used in the Cookie support dimension. Y: Enabled N: Disabled U: Unknown")
    sa += newColumn("country", IntegerType, true, "Numeric ID representing values found in the country.tsv lookup. Used in the Top level domains report in Reports & Analytics.")
    sa += newDefaultColumn("ct_connect_type", "Related to the connection_type column. Most common values are LAN/Wifi, Mobile Carrier, and Modem.")
    sa += newColumn("curr_factor", IntegerType, true, "Determines the currency decimal place, and is used for currency conversion. For example, USD uses two decimal places, so this column value would be 2.")
    sa += newDefaultColumn("curr_rate", "The exchange rate when the transaction occurred. Adobe partners with XE to determine the current day???s exchange rate.")
    sa += newDefaultColumn("currency", "The currency code that was used during the transaction.")
    sa += newColumn("cust_hit_time_gmt", IntegerType, true, "Timestamp-enabled report suites only. The timestamp sent with the hit, based in Unix time.")
    sa += newDefaultColumn("cust_visid", "If a custom visitor ID is set, it is populated in this column.")
    sa += newDefaultColumn("daily_visitor", "Flag to determine if the hit is a new daily visitor.")
    sa += newDefaultColumn("dataprivacyconsentoptin", "Variable used in the Consent management opt-in dimension. Multiple values can be present per hit, separated by a pipe (|). Valid values include DMP and SELL.")
    sa += newDefaultColumn("dataprivacyconsentoptout","Variable used in the Consent management opt-out dimension. Multiple values can be present per hit, separated by a pipe (|). Valid values include SSF, DMP, and SELL")
    sa += newDefaultColumn("date_time", "The time of the hit in readable format, based on the report suite???s time zone")
    sa += newDefaultColumn("domain", "Variable used in the Domain dimension. Based on the visitor???s internet access point.")
    sa += newDefaultColumn("duplicate_events", "Lists each event that was counted as a duplicate.")
    sa += newDefaultColumn("duplicate_purchase", "Flag indicating that the purchase event for this hit is ignored because it is a duplicate." )
    sa += newDefaultColumn("duplicated_from", "Only used in report suites containing hit copy VISTA rules. Indicates which report suite that the hit was copied from.")
    sa += newDefaultColumn("ef_id", "The ef_id used in Adobe Advertising Cloud integrations.")

    for(a <- 1 to 250) {
        sa += newDefaultColumn("evar"+a, "Custom variables 1-250. Used in eVar dimensions. Each organization uses eVars differently. The best place for more information on how your organization populates respective eVars would be a solution design document specific to your organization.")
    }

    sa += newColumn("exclude_hit", IntegerType, true, "Flag indicating that the hit is excluded from reporting. The visit_num column is not incremented for excluded hits.1: Not used. Part of a scrapped feature. 2: Not used. Part of a scrapped feature. 3: No longer used. User agent exclusion 4: Exclusion based on IP address 5: Vital hit info missing, such as page_url, pagename, page_event, or event_list 6: JavaScript did not correctly process hit 7: Account-specific exclusion, such as in a VISTA rules 8: Not used. Alternate account-specific exclusion. 9: Not used. Part of a scrapped feature. 10: Invalid currency code 11: Hit missing a timestamp on a timestamp-only report suite, or a hit contained a timestamp on a non-timestamp report suite 12: Not used. Part of a scrapped feature. 13: Not used. Part of a scrapped feature. 14: Target hit that did not match up with an Analytics hit 15: Not currently used. 16: Advertising Cloud hit that did not match up to an Analytics hit")

    for(a <- 1 to 250) {
        sa += newDefaultColumn("post_evar"+a, "Columns with a post_ prefix contain the value after processing. Examples that can change a value are variable persistence, processing rules, VISTA rules, currency conversion, or other server-side logic Adobe applies. Adobe recommends using the post_ version of a column where possible.")
    }

    sa
}

buildAdobeSchema()

/*

event_list
exclude_hit
first_hit_page_url
first_hit_pagename
first_hit_ref_domain
first_hit_ref_type
first_hit_referrer
first_hit_time_gmt
geo_city
geo_country
geo_dma
geo_region
geo_zip
hier1
hier2
hier3
hier4
hier5
hit_source
hit_time_gmt
hitid_high
hitid_low
homepage
hourly_visitor
ip
ip2
ipv6
j_jscript
java_enabled
javascript
language
last_hit_time_gmt
last_purchase_num
last_purchase_time_gmt
latlon1
latlon23
latlon45
mc_audiences
mcvisid
mobile_id
mobileacquisitionclicks
mobileaction
mobileactioninapptime
mobileactiontotaltime
mobileappid
mobileappperformanceaffectedusers
mobileappperformanceappid
mobileappperformanceappid.app-perf-app-name
mobileappperformanceappid.app-perf-platform
mobileappperformancecrashes
mobileappperformancecrashid
mobileappperformancecrashid.app-perf-crash-name
mobileappperformanceloads
mobileappstoreavgrating
mobileappstoredownloads
mobileappstoreinapprevenue
mobileappstoreinapproyalties
mobileappstoreobjectid
mobileappstoreobjectid.app-store-user
mobileappstoreobjectid.application-name
mobileappstoreobjectid.application-version
mobileappstoreobjectid.appstore-name
mobileappstoreobjectid.category-name
mobileappstoreobjectid.country-name
mobileappstoreobjectid.device-manufacturer
mobileappstoreobjectid.device-name
mobileappstoreobjectid.in-app-name
mobileappstoreobjectid.platform-name-version
mobileappstoreobjectid.rank-category-type
mobileappstoreobjectid.region-name
mobileappstoreobjectid.review-comment
mobileappstoreobjectid.review-title
mobileappstoreoneoffrevenue
mobileappstoreoneoffroyalties
mobileappstorepurchases
mobileappstorerank
mobileappstorerankdivisor
mobileappstorerating
mobileappstoreratingdivisor
mobileavgprevsessionlength
mobilebeaconmajor
mobilebeaconminor
mobilebeaconproximity
mobilebeaconuuid
mobilecampaigncontent
mobilecampaignmedium
mobilecampaignname
mobilecampaignsource
mobilecampaignterm
mobilecrashes
mobilecrashrate
mobiledailyengagedusers
mobiledayofweek
mobiledayssincefirstuse
mobiledayssincelastupgrade
mobiledayssincelastuse
mobiledeeplinkid
mobiledeeplinkid.name
mobiledevice
mobilehourofday
mobileinstalldate
mobileinstalls
mobilelaunches
mobilelaunchessincelastupgrade
mobilelaunchnumber
mobileltv
mobileltvtotal
mobilemessagebuttonname
mobilemessageclicks
mobilemessageid
mobilemessageid.dest
mobilemessageid.name
mobilemessageid.type
mobilemessageimpressions
mobilemessageonline
mobilemessagepushoptin
mobilemessagepushpayloadid
mobilemessagepushpayloadid.name
mobilemessageviews
mobilemonthlyengagedusers
mobileosenvironment
mobileosversion
mobileplaceaccuracy
mobileplacecategory
mobileplacedwelltime
mobileplaceentry
mobileplaceexit
mobileplaceid
mobileprevsessionlength
mobilepushoptin
mobilepushpayloadid
mobilerelaunchcampaigncontent
mobilerelaunchcampaignmedium
mobilerelaunchcampaignsource
mobilerelaunchcampaignterm
mobilerelaunchcampaigntrackingcode
mobilerelaunchcampaigntrackingcode.name
mobileresolution
mobileupgrades
monthly_visitor
mvvar1
mvvar1_instances
mvvar2
mvvar2_instances
mvvar3
mvvar3_instances
namespace
new_visit
os
p_plugins
page_event
page_event_var1
page_event_var2
page_event_var3
page_type
page_url
pagename
paid_search
partner_plugins
persistent_cookie
plugins
pointofinterest
pointofinterestdistance
post_adclassificationcreative
post_adload
post_browser_height
post_browser_width
post_campaign
post_channel
post_clickmaplink
post_clickmaplinkbyregion
post_clickmappage
post_clickmapregion
post_cookies
post_currency
post_cust_hit_time_gmt
post_cust_visid
post_ef_id

POST EVARS 



post_event_list
post_hier1
post_hier2
post_hier3
post_hier4
post_hier5
post_java_enabled
post_keywords
post_mc_audiences
post_mobileaction
post_mobileappid
post_mobilecampaigncontent
post_mobilecampaignmedium
post_mobilecampaignname
post_mobilecampaignsource
post_mobilecampaignterm
post_mobiledayofweek
post_mobiledayssincefirstuse
post_mobiledayssincelastuse
post_mobiledevice
post_mobilehourofday
post_mobileinstalldate
post_mobilelaunchnumber
post_mobileltv
post_mobilemessagebuttonname
post_mobilemessageclicks
post_mobilemessageid
post_mobilemessageid.dest
post_mobilemessageid.name
post_mobilemessageid.type
post_mobilemessageimpressions
post_mobilemessageonline
post_mobilemessagepushoptin
post_mobilemessagepushpayloadid
post_mobilemessagepushpayloadid.name
post_mobilemessageviews
post_mobileosversion
post_mobilepushoptin
post_mobilepushpayloadid
post_mobileresolution
post_mvvar1
post_mvvar1_instances
post_mvvar2
post_mvvar2_instances
post_mvvar3
post_mvvar3_instances
post_page_event
post_page_event_var1
post_page_event_var2
post_page_event_var3
post_page_type
post_page_url
post_pagename
post_pagename_no_url
post_partner_plugins
post_persistent_cookie
post_pointofinterest
post_pointofinterestdistance
post_product_list
post_prop1
post_prop2
post_prop3
post_prop4
post_prop5
post_prop6
post_prop7
post_prop8
post_prop9
post_prop10
post_prop11
post_prop12
post_prop13
post_prop14
post_prop15
post_prop16
post_prop17
post_prop18
post_prop19
post_prop20
post_prop21
post_prop22
post_prop23
post_prop24
post_prop25
post_prop26
post_prop27
post_prop28
post_prop29
post_prop30
post_prop31
post_prop32
post_prop33
post_prop34
post_prop35
post_prop36
post_prop37
post_prop38
post_prop39
post_prop40
post_prop41
post_prop42
post_prop43
post_prop44
post_prop45
post_prop46
post_prop47
post_prop48
post_prop49
post_prop50
post_prop51
post_prop52
post_prop53
post_prop54
post_prop55
post_prop56
post_prop57
post_prop58
post_prop59
post_prop60
post_prop61
post_prop62
post_prop63
post_prop64
post_prop65
post_prop66
post_prop67
post_prop68
post_prop69
post_prop70
post_prop71
post_prop72
post_prop73
post_prop74
post_prop75
post_purchaseid
post_referrer
post_s_kwcid
post_search_engine
post_socialaccountandappids
post_socialassettrackingcode
post_socialauthor
post_socialaveragesentiment
post_socialaveragesentiment (deprecated)
post_socialcontentprovider
post_socialfbstories
post_socialfbstorytellers
post_socialinteractioncount
post_socialinteractiontype
post_sociallanguage
post_sociallatlong
post_sociallikeadds
post_sociallink
post_sociallink (deprecated)
post_socialmentions
post_socialowneddefinitioninsighttype
post_socialowneddefinitioninsightvalue
post_socialowneddefinitionmetric
post_socialowneddefinitionpropertyvspost
post_socialownedpostids
post_socialownedpropertyid
post_socialownedpropertyname
post_socialownedpropertypropertyvsapp
post_socialpageviews
post_socialpostviews
post_socialproperty
post_socialproperty (deprecated)
post_socialpubcomments
post_socialpubposts
post_socialpubrecommends
post_socialpubsubscribers
post_socialterm
post_socialtermslist
post_socialtermslist (deprecated)
post_socialtotalsentiment
post_state
post_survey
post_t_time_info
post_tnt
post_tnt_action
post_transactionid
post_user_server
post_video
post_videoad
post_videoadinpod
post_videoadlength
post_videoadname
post_videoadplayername
post_videoadpod
post_videoadvertiser
post_videoauthorized
post_videocampaign
post_videochannel
post_videochapter
post_videocontenttype
post_videodaypart
post_videoepisode
post_videofeedtype
post_videogenre
post_videolength
post_videomvpd
post_videoname
post_videonetwork
post_videopath
post_videoplayername
post_videoqoebitrateaverageevar
post_videoqoebitratechangecountevar
post_videoqoebuffercountevar
post_videoqoebuffertimeevar
post_videoqoedroppedframecountevar
post_videoqoeerrorcountevar
post_videoqoeplayersdkerrors
post_videoqoetimetostartevar
post_videoseason
post_videosegment
post_videoshow
post_videoshowtype
post_visid_high
post_visid_low
post_visid_type
post_zip
prev_page
product_list
product_merchandising
prop1
prop2
prop3
prop4
prop5
prop6
prop7
prop8
prop9
prop10
prop11
prop12
prop13
prop14
prop15
prop16
prop17
prop18
prop19
prop20
prop21
prop22
prop23
prop24
prop25
prop26
prop27
prop28
prop29
prop30
prop31
prop32
prop33
prop34
prop35
prop36
prop37
prop38
prop39
prop40
prop41
prop42
prop43
prop44
prop45
prop46
prop47
prop48
prop49
prop50
prop51
prop52
prop53
prop54
prop55
prop56
prop57
prop58
prop59
prop60
prop61
prop62
prop63
prop64
prop65
prop66
prop67
prop68
prop69
prop70
prop71
prop72
prop73
prop74
prop75
purchaseid
quarterly_visitor
ref_domain
ref_type
referrer
resolution
s_kwcid
s_resolution
sampled_hit
search_engine
search_page_num
secondary_hit
service
socialaccountandappids
socialassettrackingcode
socialauthor
socialaveragesentiment
socialaveragesentiment (deprecated)
socialcontentprovider
socialfbstories
socialfbstorytellers
socialinteractioncount
socialinteractiontype
sociallanguage
sociallatlong
sociallikeadds
sociallink
sociallink (deprecated)
socialmentions
socialowneddefinitioninsighttype
socialowneddefinitioninsightvalue
socialowneddefinitionmetric
socialowneddefinitionpropertyvspost
socialownedpostids
socialownedpropertyid
socialownedpropertyname
socialownedpropertypropertyvsapp
socialpageviews
socialpostviews
socialproperty
socialproperty (deprecated)
socialpubcomments
socialpubposts
socialpubrecommends
socialpubsubscribers
socialterm
socialtermslist
socialtermslist (deprecated)
socialtotalsentiment
sourceid
state
stats_server
survey_instances
t_time_info
tnt
tnt_action
tnt_instances
tnt_post_vista
transactionid
truncated_hit
ua_color
ua_os
ua_pixels
user_agent
user_hash
user_server
userid
username
va_closer_detail
va_closer_id
va_finder_detail
va_finder_id
va_instance_event
va_new_engagement
video
videoad
videoadinpod
videoadlength
videoadname
videoadplayername
videoadpod
videoadvertiser
videoaudioalbum
videoaudioartist
videoaudioauthor
videoaudiolabel
videoaudiopublisher
videoaudiostation
videoauthorized
videoaverageminuteaudience
videocampaign
videochannel
videochapter
videochaptercomplete
videochapterstart
videochaptertime
videocontenttype
videodaypart
videoepisode
videofeedtype
videogenre
videolength
videomvpd
videoname
videonetwork
videopath
videopause
videopausecount
videopausetime
videoplay
videoplayername
videoprogress10
videoprogress25
videoprogress50
videoprogress75
videoprogress96
videoqoebitrateaverage
videoqoebitrateaverageevar
videoqoebitratechange
videoqoebitratechangecountevar
videoqoebuffer
videoqoebuffercountevar
videoqoebuffertimeevar
videoqoedropbeforestart
videoqoedroppedframecountevar
videoqoedroppedframes
videoqoeerror
videoqoeerrorcountevar
videoqoeextneralerrors
videoqoeplayersdkerrors
videoqoetimetostartevar
videoresume
videoseason
videosegment
videoshow
videoshowtype
videostreamtype
videototaltime
videouniquetimeplayed
visid_high
visid_low
visid_new
visid_timestamp
visid_type
visit_keywords
visit_num
visit_page_num
visit_ref_domain
visit_ref_type
visit_referrer
visit_search_engine
visit_start_page_url
visit_start_pagename
visit_start_time_gmt
weekly_visitor
yearly_visitor
zip 

*/
