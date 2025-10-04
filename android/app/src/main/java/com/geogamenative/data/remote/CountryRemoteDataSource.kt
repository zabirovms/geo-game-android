package com.geogamenative.data.remote

import com.geogamenative.data.model.Country
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRemoteDataSource @Inject constructor() {
    
    // In a real app, this would fetch from an API
    // For now, we'll use the static data from the React Native app
    suspend fun getAllCountries(): List<Country> {
        return getStaticCountries()
    }
    
    private fun getStaticCountries(): List<Country> {
        return listOf(
            // Africa
            Country("AO", "Ангола", "Angola", "Ангола", "africa", "Луанда", "Luanda", "Луанда", -8.8308, 13.2453),
            Country("BF", "Буркина-Фасо", "Burkina Faso", "Буркина-Фасо", "africa", "Уагадугу", "Ouagadougou", "Уагадугу", 12.2383, -1.5616),
            Country("BI", "Бурунди", "Burundi", "Бурунди", "africa", "Бужумбура", "Bujumbura", "Бужумбура", -3.3731, 29.9189),
            Country("BJ", "Бенин", "Benin", "Бенин", "africa", "Порто-Ново", "Porto-Novo", "Порто-Ново", 6.4969, 2.6289),
            Country("BW", "Ботсвана", "Botswana", "Ботсвана", "africa", "Габороне", "Gaborone", "Габороне", -24.6282, 25.9231),
            Country("CD", "Конго - Киншаса", "Congo - Kinshasa", "Конго - Киншаса", "africa", "Киншаса", "Kinshasa", "Киншаса", -4.4419, 15.2663),
            Country("CF", "Ҷумҳурии Африқои Марказӣ", "Central African Republic", "Центрально-Африканская Республика", "africa", "Банги", "Bangui", "Банги", 4.3947, 18.5582),
            Country("CG", "Конго - Браззавил", "Congo - Brazzaville", "Конго - Браззавиль", "africa", "Браззавил", "Brazzaville", "Браззавиль", -4.2634, 15.2429),
            Country("CI", "Кот-д'Ивуар", "Côte d'Ivoire", "Кот-д'Ивуар", "africa", "Ямусукро", "Yamoussoukro", "Ямусукро", 6.8276, -5.2893),
            Country("CM", "Камерун", "Cameroon", "Камерун", "africa", "Яунде", "Yaoundé", "Яунде", 3.848, 11.5021),
            Country("CV", "Кабо-Верде", "Cape Verde", "Кабо-Верде", "africa", "Прая", "Praia", "Прая", 14.9331, -23.5133),
            Country("DJ", "Ҷибути", "Djibouti", "Джибути", "africa", "Ҷибути", "Djibouti", "Джибути", 11.8251, 42.5903),
            Country("DZ", "Алҷазоир", "Algeria", "Алжир", "africa", "Алҷазоир", "Algiers", "Алжир", 28.0339, 1.6596),
            Country("EG", "Миср", "Egypt", "Египет", "africa", "Қоҳира", "Cairo", "Каир", 26.0975, 10.4515),
            Country("ET", "Эфиопия", "Ethiopia", "Эфиопия", "africa", "Аддис-Абеба", "Addis Ababa", "Аддис-Абеба", 9.145, 40.4897),
            Country("GA", "Габон", "Gabon", "Габон", "africa", "Либревил", "Libreville", "Либревиль", 0.4162, 9.4673),
            Country("GH", "Гана", "Ghana", "Гана", "africa", "Аккра", "Accra", "Аккра", 5.6037, -0.187),
            Country("GM", "Гамбия", "Gambia", "Гамбия", "africa", "Банжул", "Banjul", "Банжул", 13.4432, -16.5814),
            Country("GN", "Гвинея", "Guinea", "Гвинея", "africa", "Конакри", "Conakry", "Конакри", 9.6412, -13.5784),
            Country("GQ", "Гвинеяи Экваторӣ", "Equatorial Guinea", "Экваториальная Гвинея", "africa", "Малабо", "Malabo", "Малабо", 3.7504, 8.7371),
            Country("GW", "Гвинея-Бисау", "Guinea-Bissau", "Гвинея-Бисау", "africa", "Бисау", "Bissau", "Бисау", 11.8037, -15.1804),
            Country("KE", "Кения", "Kenya", "Кения", "africa", "Найроби", "Nairobi", "Найроби", -1.2921, 36.8219),
            Country("KM", "Комор", "Comoros", "Коморы", "africa", "Морони", "Moroni", "Морони", -11.6455, 43.3333),
            Country("LR", "Либерия", "Liberia", "Либерия", "africa", "Монровия", "Monrovia", "Монровия", 6.4281, -10.4725),
            Country("LS", "Лесото", "Lesotho", "Лесото", "africa", "Масеру", "Maseru", "Масеру", -29.6103, 27.4786),
            Country("LY", "Либия", "Libya", "Ливия", "africa", "Триполи", "Tripoli", "Триполи", 26.3351, 17.2283),
            Country("MA", "Марокаш", "Morocco", "Марокко", "africa", "Рабат", "Rabat", "Рабат", 33.9716, -6.8498),
            Country("MG", "Мадагаскар", "Madagascar", "Мадагаскар", "africa", "Антананариву", "Antananarivo", "Антананариву", -18.7669, 46.8691),
            Country("ML", "Мали", "Mali", "Мали", "africa", "Бамако", "Bamako", "Бамако", 12.6392, -8.0029),
            Country("MR", "Мавритания", "Mauritania", "Мавритания", "africa", "Нуакшот", "Nouakchott", "Нуакшот", 18.0735, -15.9582),
            Country("MU", "Маврикий", "Mauritius", "Маврикий", "africa", "Порт-Луи", "Port Louis", "Порт-Луи", -20.3484, 57.5522),
            Country("MW", "Малави", "Malawi", "Малави", "africa", "Лилонгве", "Lilongwe", "Лилонгве", -13.9626, 33.7741),
            Country("MZ", "Мозамбик", "Mozambique", "Мозамбик", "africa", "Мапуту", "Maputo", "Мапуту", -25.9692, 32.5739),
            Country("NA", "Намибия", "Namibia", "Намибия", "africa", "Виндхук", "Windhoek", "Виндхук", -22.5609, 17.0658),
            Country("NE", "Нигер", "Niger", "Нигер", "africa", "Ниамей", "Niamey", "Ниамей", 13.5137, 2.1098),
            Country("NG", "Нигерия", "Nigeria", "Нигерия", "africa", "Абуджа", "Abuja", "Абуджа", 9.0765, 7.3986),
            Country("RW", "Руанда", "Rwanda", "Руанда", "africa", "Кигали", "Kigali", "Кигали", -1.9441, 30.0619),
            Country("SC", "Ҷазираҳои Сейшел", "Seychelles", "Сейшельские Острова", "africa", "Виктория", "Victoria", "Виктория", -4.6796, 55.492),
            Country("SD", "Судан", "Sudan", "Судан", "africa", "Хартум", "Khartoum", "Хартум", 15.5007, 32.5599),
            Country("SL", "Сьерра-Леоне", "Sierra Leone", "Сьерра-Леоне", "africa", "Фритаун", "Freetown", "Фритаун", 8.4606, -13.2317),
            Country("SN", "Сенегал", "Senegal", "Сенегал", "africa", "Дакар", "Dakar", "Дакар", 14.6928, -17.4467),
            Country("SO", "Сомали", "Somalia", "Сомали", "africa", "Могадишо", "Mogadishu", "Могадишо", 2.0469, 45.3182),
            Country("SS", "Судани Ҷанубӣ", "South Sudan", "Южный Судан", "africa", "Ҷуба", "Juba", "Джуба", 4.8594, 31.5712),
            Country("ST", "Сан-Томе ва Принсипи", "São Tomé & Príncipe", "Сан-Томе и Принсипи", "africa", "Сан-Томе", "São Tomé", "Сан-Томе", 0.1864, 6.6131),
            Country("SZ", "Свазиленд", "Swaziland", "Свазиленд", "africa", "Лобамба", "Lobamba", "Лобамба", -26.4194, 31.2001),
            Country("TD", "Чад", "Chad", "Чад", "africa", "Н'Джамена", "N'Djamena", "Н'Джамена", 12.1348, 15.0557),
            Country("TG", "Того", "Togo", "Того", "africa", "Ломе", "Lomé", "Ломе", 6.1725, 1.2314),
            Country("TN", "Тунис", "Tunisia", "Тунис", "africa", "Тунис", "Tunis", "Тунис", 36.8065, 10.1815),
            Country("TZ", "Танзания", "Tanzania", "Танзания", "africa", "Додома", "Dodoma", "Додома", -6.163, 35.7516),
            Country("UG", "Уганда", "Uganda", "Уганда", "africa", "Кампала", "Kampala", "Кампала", 0.3476, 32.5825),
            Country("ZA", "Ҷумҳурии Африқои Ҷанубӣ", "South Africa", "Южно-Африканская Республика", "africa", "Претория", "Pretoria", "Претория", -25.7479, 28.2293),
            Country("ZM", "Замбия", "Zambia", "Замбия", "africa", "Лусака", "Lusaka", "Лусака", -15.3875, 28.3228),
            Country("ZW", "Зимбабве", "Zimbabwe", "Зимбабве", "africa", "Хараре", "Harare", "Хараре", -17.8292, 31.0522),
            
            // Asia - Key countries including Tajikistan
            Country("TJ", "Тоҷикистон", "Tajikistan", "Таджикистан", "asia", "Душанбе", "Dushanbe", "Душанбе", 38.5358, 68.7791),
            Country("AF", "Афғонистон", "Afghanistan", "Афганистан", "asia", "Кобул", "Kabul", "Кабул", 33.9391, 67.7100),
            Country("UZ", "Ӯзбекистон", "Uzbekistan", "Узбекистан", "asia", "Тошканд", "Tashkent", "Ташкент", 41.3775, 64.5853),
            Country("KZ", "Қазоқистон", "Kazakhstan", "Казахстан", "asia", "Нур-Султон", "Nur-Sultan", "Нур-Султан", 48.0196, 66.9237),
            Country("KG", "Қирғизистон", "Kyrgyzstan", "Кыргызстан", "asia", "Бишкек", "Bishkek", "Бишкек", 41.2044, 74.7661),
            Country("TM", "Туркманистон", "Turkmenistan", "Туркменистан", "asia", "Ашқобод", "Ashgabat", "Ашхабад", 38.9697, 59.5563),
            Country("CN", "Хитой", "China", "Китай", "asia", "Пекин", "Beijing", "Пекин", 35.8617, 104.1954),
            Country("IN", "Ҳиндустон", "India", "Индия", "asia", "Деҳлии Нав", "New Delhi", "Нью-Дели", 20.5937, 78.9629),
            Country("PK", "Покистон", "Pakistan", "Пакистан", "asia", "Исломобод", "Islamabad", "Исламабад", 30.3753, 69.3451),
            Country("IR", "Эрон", "Iran", "Иран", "asia", "Теҳрон", "Tehran", "Тегеран", 32.4279, 53.6880),
            Country("RU", "Русия", "Russia", "Россия", "europe", "Москва", "Moscow", "Москва", 61.5240, 105.3188)
        )
    }
}
