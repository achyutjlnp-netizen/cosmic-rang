package com.example.domain.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.example.core.i18n.LocalizedText
import com.example.ui.theme.CelestialGold
import com.example.ui.theme.CosmicNavy
import com.example.ui.theme.CosmicSurfaceElevated
import com.example.ui.theme.MoonIvory
import com.example.ui.theme.MysticPurple

enum class CosmicTab {
    HOME,
    DESIGN_SYSTEM,
    ROADMAP
}

enum class ServiceCategory {
    ALL,
    CHARTS_AND_MATCH,
    GUIDANCE_AND_SPACE,
    REPORTS_AND_CONSULTS
}

enum class ServiceIconType {
    VEDIC_ASTROLOGY,
    KUNDALI_GENERATION,
    KUNDALI_MATCHING,
    NUMEROLOGY,
    VASTU,
    RASHIFAL,
    AI_REPORTS,
    CONSULTATIONS
}

@Immutable
data class CosmicServiceSpec(
    val id: String,
    val title: LocalizedText,
    val subtitle: LocalizedText,
    val category: ServiceCategory,
    val iconType: ServiceIconType,
    val requiredInputs: LocalizedText,
    val integrityGuarantee: LocalizedText,
    val deliveryMilestone: LocalizedText,
    val isLiveNow: Boolean = false
)

@Immutable
data class ArchitecturePillar(
    val id: String,
    val title: LocalizedText,
    val description: LocalizedText
)

@Immutable
data class PlatformRoleSpec(
    val id: String,
    val claimKey: String,
    val title: LocalizedText,
    val scope: LocalizedText
)

@Immutable
data class ColorTokenSpec(
    val name: LocalizedText,
    val hex: String,
    val color: Color,
    val usage: LocalizedText
)

@Immutable
data class MilestoneSpec(
    val number: Int,
    val title: LocalizedText,
    val summary: LocalizedText,
    val deliverables: LocalizedText,
    val isActive: Boolean
)

object CosmicCatalog {

    val services: List<CosmicServiceSpec> = listOf(
        CosmicServiceSpec(
            id = "vedic_astrology",
            title = LocalizedText(
                en = "Vedic Astrology",
                hi = "वैदिक ज्योतिष"
            ),
            subtitle = LocalizedText(
                en = "Sidereal Lahiri ephemeris foundations, dasha timelines, and planetary strength synthesis.",
                hi = "लाहिड़ी अयनांश आधारित खगोलीय गणना, दशा चक्र और ग्रह बल विश्लेषण।"
            ),
            category = ServiceCategory.CHARTS_AND_MATCH,
            iconType = ServiceIconType.VEDIC_ASTROLOGY,
            requiredInputs = LocalizedText(
                en = "Exact birth date, birth time (HH:MM), and verified birth coordinates with timezone.",
                hi = "सटीक जन्म तिथि, जन्म समय और अक्षांश-देशांतर सहित जन्म स्थान।"
            ),
            integrityGuarantee = LocalizedText(
                en = "All planetary longitudes are computed strictly by the server-side ephemeris engine—never approximated in the UI.",
                hi = "सभी ग्रह स्थिति की गणना केवल सर्वर-स्तरीय खगोलीय इंजन द्वारा की जाएगी।"
            ),
            deliveryMilestone = LocalizedText(
                en = "Milestone 3 · Ephemeris & Chart Engine",
                hi = "चरण 3 · खगोलीय और कुंडली इंजन"
            )
        ),
        CosmicServiceSpec(
            id = "kundali_generation",
            title = LocalizedText(
                en = "Kundali Generation",
                hi = "कुंडली निर्माण"
            ),
            subtitle = LocalizedText(
                en = "North & South Indian Lagna, Navamsa (D9), and Vimshottari Dasha charts stored privately.",
                hi = "उत्तर एवं दक्षिण भारतीय लग्न, नवांश (D9) और विंशोत्तरी दशा चार्ट।"
            ),
            category = ServiceCategory.CHARTS_AND_MATCH,
            iconType = ServiceIconType.KUNDALI_GENERATION,
            requiredInputs = LocalizedText(
                en = "Saved Birth Profile (full name, date, exact time, geocoded city, and preferred chart style).",
                hi = "सहेजी गई जन्म प्रोफ़ाइल (नाम, तिथि, सटीक समय, शहर और चार्ट शैली)।"
            ),
            integrityGuarantee = LocalizedText(
                en = "Each generated chart is cryptographically bound to its owner UID via Firestore rules.",
                hi = "प्रत्येक कुंडली केवल उसके स्वामी के सुरक्षित खाते से जुड़ी रहती है।"
            ),
            deliveryMilestone = LocalizedText(
                en = "Milestone 3 · Birth Profiles & Kundali Workflow",
                hi = "चरण 3 · जन्म प्रोफ़ाइल और कुंडली कार्यप्रवाह"
            )
        ),
        CosmicServiceSpec(
            id = "kundali_matching",
            title = LocalizedText(
                en = "Kundali Matching",
                hi = "कुंडली मिलान"
            ),
            subtitle = LocalizedText(
                en = "Ashtakoota Guna Milan, Manglik evaluation, and relational compatibility overview.",
                hi = "अष्टकूट गुण मिलान, मांगलिक विचार और पारिवारिक सामंजस्य समीक्षा।"
            ),
            category = ServiceCategory.CHARTS_AND_MATCH,
            iconType = ServiceIconType.KUNDALI_MATCHING,
            requiredInputs = LocalizedText(
                en = "Two complete Birth Profiles with verified birth times and place coordinates.",
                hi = "सटीक जन्म समय और स्थान सहित दो पूर्ण जन्म प्रोफ़ाइल।"
            ),
            integrityGuarantee = LocalizedText(
                en = "Compatibility scoring is strictly deterministic on the backend; no synthetic Guna scores are rendered client-side.",
                hi = "गुण मिलान स्कोर पूरी तरह सर्वर गणना पर आधारित होगा।"
            ),
            deliveryMilestone = LocalizedText(
                en = "Milestone 3 · Relational Matching Service",
                hi = "चरण 3 · मिलान सेवा"
            )
        ),
        CosmicServiceSpec(
            id = "numerology",
            title = LocalizedText(
                en = "Numerology",
                hi = "अंक ज्योतिष"
            ),
            subtitle = LocalizedText(
                en = "Chaldean & Pythagorean name vibration, Moolank, Bhagyank, and personal year cycles.",
                hi = "मूलांक, भाग्यांक, नामांक कंपन और व्यक्तिगत वर्ष चक्र विश्लेषण।"
            ),
            category = ServiceCategory.GUIDANCE_AND_SPACE,
            iconType = ServiceIconType.NUMEROLOGY,
            requiredInputs = LocalizedText(
                en = "Legal or everyday full name and date of birth.",
                hi = "पूरा नाम और जन्म तिथि।"
            ),
            integrityGuarantee = LocalizedText(
                en = "Transparent digit-reduction audit trails accompany every numerology calculation.",
                hi = "प्रत्येक अंक गणना के साथ स्पष्ट चरणबद्ध विवरण उपलब्ध रहेगा।"
            ),
            deliveryMilestone = LocalizedText(
                en = "Milestone 4 · Numerology & Rashifal",
                hi = "चरण 4 · अंक ज्योतिष और राशिफल"
            )
        ),
        CosmicServiceSpec(
            id = "vastu",
            title = LocalizedText(
                en = "Vastu Shastra",
                hi = "वास्तु शास्त्र"
            ),
            subtitle = LocalizedText(
                en = "Directional spatial audits for homes and workspaces with floor-plan annotation.",
                hi = "आवास और कार्यस्थल के लिए दिशा-आधारित वास्तु मूल्यांकन और योजना।"
            ),
            category = ServiceCategory.GUIDANCE_AND_SPACE,
            iconType = ServiceIconType.VASTU,
            requiredInputs = LocalizedText(
                en = "Floor-plan upload (Firebase Storage), compass entrance bearing, and property category.",
                hi = "फ्लोर प्लान अपलोड, मुख्य द्वार की दिशा और संपत्ति प्रकार।"
            ),
            integrityGuarantee = LocalizedText(
                en = "Uploaded floor plans reside in private per-user Storage buckets accessible only to the owner and assigned specialist.",
                hi = "अपलोड किए गए फ्लोर प्लान निजी स्टोरेज में केवल स्वामी और नियुक्त विशेषज्ञ के लिए सुलभ रहते हैं।"
            ),
            deliveryMilestone = LocalizedText(
                en = "Milestone 4 · Vastu Projects & Storage",
                hi = "चरण 4 · वास्तु प्रोजेक्ट और स्टोरेज"
            )
        ),
        CosmicServiceSpec(
            id = "rashifal",
            title = LocalizedText(
                en = "Daily Rashifal",
                hi = "दैनिक राशिफल"
            ),
            subtitle = LocalizedText(
                en = "Moon-sign (Chandra Rashi) transit briefings localized in Hindi, English, and regional scripts.",
                hi = "चंद्र राशि गोचर पर आधारित दैनिक, साप्ताहिक और मासिक मार्गदर्शन।"
            ),
            category = ServiceCategory.GUIDANCE_AND_SPACE,
            iconType = ServiceIconType.RASHIFAL,
            requiredInputs = LocalizedText(
                en = "Selected Chandra Rashi or linked primary Birth Profile.",
                hi = "चयनित चंद्र राशि या मुख्य जन्म प्रोफ़ाइल।"
            ),
            integrityGuarantee = LocalizedText(
                en = "Transit briefings follow daily planetary ephemeris snapshots without generic recycled text.",
                hi = "राशिफल दैनिक ग्रह गोचर स्नैपशॉट पर आधारित रहता है।"
            ),
            deliveryMilestone = LocalizedText(
                en = "Milestone 4 · Rashifal Feed",
                hi = "चरण 4 · राशिफल फ़ीड"
            )
        ),
        CosmicServiceSpec(
            id = "ai_reports",
            title = LocalizedText(
                en = "Personalized AI Reports",
                hi = "व्यक्तिगत एआई रिपोर्ट"
            ),
            subtitle = LocalizedText(
                en = "Server-side Gemini synthesis grounded strictly in your verified Kundali & Dasha JSON.",
                hi = "आपकी प्रमाणित कुंडली और दशा डेटा पर आधारित सर्वर-स्तरीय विस्तृत रिपोर्ट।"
            ),
            category = ServiceCategory.REPORTS_AND_CONSULTS,
            iconType = ServiceIconType.AI_REPORTS,
            requiredInputs = LocalizedText(
                en = "Verified Birth Profile, computed chart payload, focus domain, and preferred output language.",
                hi = "सत्यापित जन्म प्रोफ़ाइल, गणना किया गया चार्ट डेटा और पसंदीदा भाषा।"
            ),
            integrityGuarantee = LocalizedText(
                en = "Gemini API runs strictly server-side with zero client API keys, grounded exclusively in deterministic chart outputs.",
                hi = "जेमिनी एआई केवल सर्वर पर चलता है—क्लाइंट में कोई एपीआई कुंजी नहीं रखी जाती।"
            ),
            deliveryMilestone = LocalizedText(
                en = "Milestone 5 · AI Interpretation Engine",
                hi = "चरण 5 · एआई व्याख्या इंजन"
            )
        ),
        CosmicServiceSpec(
            id = "consultations",
            title = LocalizedText(
                en = "Astrologer Consultations",
                hi = "ज्योतिषी परामर्श"
            ),
            subtitle = LocalizedText(
                en = "Scheduled 1:1 sessions with verified Acharyas, server-verified orders, and session notes.",
                hi = "सत्यापित आचार्यों के साथ 1:1 सत्र, सुरक्षित भुगतान और सत्र सारांश।"
            ),
            category = ServiceCategory.REPORTS_AND_CONSULTS,
            iconType = ServiceIconType.CONSULTATIONS,
            requiredInputs = LocalizedText(
                en = "Preferred astrologer, consultation slot, linked Birth Profile, and verified payment order.",
                hi = "चयनित आचार्य, समय स्लॉट, जन्म प्रोफ़ाइल और सत्यापित ऑर्डर।"
            ),
            integrityGuarantee = LocalizedText(
                en = "Bookings confirm strictly after server-side payment webhook verification—never via client-only state.",
                hi = "बुकिंग केवल सर्वर-स्तरीय भुगतान सत्यापन के बाद ही पुष्ट होती है।"
            ),
            deliveryMilestone = LocalizedText(
                en = "Milestone 6 · Consultations, Orders & Admin",
                hi = "चरण 6 · परामर्श, ऑर्डर और प्रशासन"
            )
        )
    )

    val pillars: List<ArchitecturePillar> = listOf(
        ArchitecturePillar(
            id = "ephemeris",
            title = LocalizedText(
                en = "Zero Fabricated Charts",
                hi = "प्रामाणिक खगोलीय गणना"
            ),
            description = LocalizedText(
                en = "Planetary positions, Vargas, and Dashas come strictly from the server ephemeris layer.",
                hi = "ग्रह स्थिति और दशाएं केवल सर्वर खगोलीय इंजन से प्राप्त होती हैं।"
            )
        ),
        ArchitecturePillar(
            id = "privacy",
            title = LocalizedText(
                en = "Strict Record Isolation",
                hi = "कठोर डेटा गोपनीयता"
            ),
            description = LocalizedText(
                en = "Firestore rules and custom claims ensure customers never access another customer's records.",
                hi = "फायरस्टोर नियम सुनिश्चित करते हैं कि निजी रिकॉर्ड केवल स्वामी को ही दिखें।"
            )
        ),
        ArchitecturePillar(
            id = "server_ai",
            title = LocalizedText(
                en = "Server-Side AI",
                hi = "सर्वर-स्तरीय एआई"
            ),
            description = LocalizedText(
                en = "Gemini synthesis runs in protected server runtime with zero exposed client credentials.",
                hi = "एआई व्याख्या सुरक्षित सर्वर में होती है, क्लाइंट में कोई कुंजी नहीं रहती।"
            )
        ),
        ArchitecturePillar(
            id = "multilingual",
            title = LocalizedText(
                en = "Scalable Multilingual",
                hi = "बहुभाषी संरचना"
            ),
            description = LocalizedText(
                en = "English, Hindi, and extensible regional locale adapters built into every layer.",
                hi = "हिंदी, अंग्रेज़ी और अन्य क्षेत्रीय भाषाओं के लिए स्केलेबल समर्थन।"
            )
        )
    )

    val roles: List<PlatformRoleSpec> = listOf(
        PlatformRoleSpec(
            id = "customer",
            claimKey = "role: customer",
            title = LocalizedText(en = "Customer", hi = "ग्राहक"),
            scope = LocalizedText(
                en = "Owns personal birth profiles, Kundalis, Vastu uploads, reports, orders, and booked sessions.",
                hi = "अपनी जन्म प्रोफ़ाइल, कुंडली, वास्तु प्रोजेक्ट, रिपोर्ट और परामर्श का प्रबंधन।"
            )
        ),
        PlatformRoleSpec(
            id = "astrologer",
            claimKey = "role: astrologer",
            title = LocalizedText(en = "Astrologer", hi = "ज्योतिषी"),
            scope = LocalizedText(
                en = "Accesses only assigned consultations, shared session briefs, availability slots, and chart reviews.",
                hi = "केवल आवंटित परामर्श, साझा कुंडली संदर्भ और समय सारणी तक पहुंच।"
            )
        ),
        PlatformRoleSpec(
            id = "support",
            claimKey = "role: support_agent",
            title = LocalizedText(en = "Support Agent", hi = "सहायता प्रतिनिधि"),
            scope = LocalizedText(
                en = "Audited ticket resolution and order status assistance without unmasked private chart access.",
                hi = "ऑर्डर सहायता और टिकट निवारण (गोपनीयता सीमाओं के भीतर)।"
            )
        ),
        PlatformRoleSpec(
            id = "admin",
            claimKey = "role: admin",
            title = LocalizedText(en = "Administrator", hi = "प्रशासक"),
            scope = LocalizedText(
                en = "Role claims provisioning, astrologer verification, catalog governance, and audit logs.",
                hi = "भूमिका प्रबंधन, आचार्य सत्यापन, सेवा कैटलॉग और ऑडिट लॉग।"
            )
        )
    )

    val colorTokens: List<ColorTokenSpec> = listOf(
        ColorTokenSpec(
            name = LocalizedText(en = "Cosmic Navy", hi = "कॉस्मिक नेवी"),
            hex = "#10112B",
            color = CosmicNavy,
            usage = LocalizedText(
                en = "Primary canvas, system bars, and deep atmospheric backdrop",
                hi = "मुख्य पृष्ठभूमि और गहन आकाशीय आधार"
            )
        ),
        ColorTokenSpec(
            name = LocalizedText(en = "Mystic Purple", hi = "मिस्टिक पर्पल"),
            hex = "#6E4B91",
            color = MysticPurple,
            usage = LocalizedText(
                en = "Secondary accent, nebula highlights, and role badges",
                hi = "द्वितीयक आभा, बैज और विशेष हाइलाइट्स"
            )
        ),
        ColorTokenSpec(
            name = LocalizedText(en = "Celestial Gold", hi = "सेलेस्टियल गोल्ड"),
            hex = "#D8B45B",
            color = CelestialGold,
            usage = LocalizedText(
                en = "Primary CTAs, sacred dividers, and active selections",
                hi = "मुख्य बटन, विभाजक रेखाएं और सक्रिय चयन"
            )
        ),
        ColorTokenSpec(
            name = LocalizedText(en = "Moon Ivory", hi = "मून आइवरी"),
            hex = "#F3E9D5",
            color = MoonIvory,
            usage = LocalizedText(
                en = "High-contrast headlines, body typography, and icons",
                hi = "मुख्य शीर्षक, पठनीय पाठ और आइकन"
            )
        ),
        ColorTokenSpec(
            name = LocalizedText(en = "Cosmic Slate", hi = "कॉस्मिक स्लेट"),
            hex = "#202352",
            color = CosmicSurfaceElevated,
            usage = LocalizedText(
                en = "Elevated cards, modal sheets, and interactive containers",
                hi = "कार्ड, मॉडल शीट और इंटरैक्टिव कंटेनर"
            )
        )
    )

    val milestones: List<MilestoneSpec> = listOf(
        MilestoneSpec(
            number = 1,
            title = LocalizedText(
                en = "Design System & Homepage",
                hi = "डिज़ाइन सिस्टम और होमपेज"
            ),
            summary = LocalizedText(
                en = "Stitch tokens, Cinzel & Plus Jakarta Sans typography, EN/HI i18n, adaptive navigation, and service catalog.",
                hi = "रंग टोकन, टाइपोग्राफी, हिंदी-अंग्रेज़ी समर्थन, अनुकूली नेविगेशन और सेवा निर्देशिका।"
            ),
            deliverables = LocalizedText(
                en = "Theme.kt, CosmicComponents, HomeScreen, DesignSystemScreen, ArchitectureScreen",
                hi = "थीम, पुन: प्रयोज्य कंपोनेंट्स, होमपेज और आर्किटेक्चर व्यू"
            ),
            isActive = true
        ),
        MilestoneSpec(
            number = 2,
            title = LocalizedText(
                en = "Auth, Custom Claims & Customer Shell",
                hi = "प्रमाणीकरण और ग्राहक डैशबोर्ड"
            ),
            summary = LocalizedText(
                en = "Firebase Auth + Credential Manager sign-in, server custom claims, and private customer dashboard.",
                hi = "फायरबेस प्रमाणीकरण, सर्वर कस्टम क्लेम और निजी ग्राहक डैशबोर्ड।"
            ),
            deliverables = LocalizedText(
                en = "Auth repository, role guards, Firestore security rules, customer profile shell",
                hi = "ऑथ रिपॉजिटरी, भूमिका सुरक्षा और फायरस्टोर नियम"
            ),
            isActive = false
        ),
        MilestoneSpec(
            number = 3,
            title = LocalizedText(
                en = "Birth Profiles & Kundali Engine",
                hi = "जन्म प्रोफ़ाइल और कुंडली इंजन"
            ),
            summary = LocalizedText(
                en = "Verified birth profile CRUD, server ephemeris calculation contract, Kundali charts, and Guna matching.",
                hi = "जन्म प्रोफ़ाइल प्रबंधन, सर्वर खगोलीय गणना, लग्न चार्ट और गुण मिलान।"
            ),
            deliverables = LocalizedText(
                en = "BirthProfile repository, ephemeris API client, Kundali & Match viewer",
                hi = "जन्म प्रोफ़ाइल और कुंडली चार्ट व्यूअर"
            ),
            isActive = false
        ),
        MilestoneSpec(
            number = 4,
            title = LocalizedText(
                en = "Numerology, Vastu & Rashifal",
                hi = "अंक ज्योतिष, वास्तु और राशिफल"
            ),
            summary = LocalizedText(
                en = "Numerology calculator API, Vastu floor-plan uploads to Firebase Storage, and daily Chandra Rashi feed.",
                hi = "अंक ज्योतिष सेवा, वास्तु फ्लोर प्लान अपलोड और दैनिक राशिफल फ़ीड।"
            ),
            deliverables = LocalizedText(
                en = "Vastu project workflow, Storage security rules, Rashifal reader",
                hi = "वास्तु अपलोड और राशिफल रीडर"
            ),
            isActive = false
        ),
        MilestoneSpec(
            number = 5,
            title = LocalizedText(
                en = "Server-Side Gemini AI Reports",
                hi = "सर्वर-स्तरीय एआई रिपोर्ट"
            ),
            summary = LocalizedText(
                en = "Structured prompt grounding over verified chart JSON, localized report generation, and PDF/archive storage.",
                hi = "सत्यापित चार्ट डेटा पर आधारित एआई रिपोर्ट निर्माण और सुरक्षित संग्रह।"
            ),
            deliverables = LocalizedText(
                en = "Server AI orchestration endpoint, typed report viewer, language-aware prompts",
                hi = "सर्वर एआई एंडपॉइंट और रिपोर्ट व्यूअर"
            ),
            isActive = false
        ),
        MilestoneSpec(
            number = 6,
            title = LocalizedText(
                en = "Consultations, Payments & Admin",
                hi = "परामर्श, भुगतान और प्रशासन"
            ),
            summary = LocalizedText(
                en = "Astrologer slots, order checkout with webhook-verified payments, support desk, and admin governance.",
                hi = "आचार्य स्लॉट बुकिंग, वेबहुक-सत्यापित भुगतान, सहायता और व्यवस्थापक पैनल।"
            ),
            deliverables = LocalizedText(
                en = "Order & payment state machine, consultation workspace, admin role console",
                hi = "ऑर्डर प्रबंधन, परामर्श कार्यक्षेत्र और एडमिन कंसोल"
            ),
            isActive = false
        )
    )
}
