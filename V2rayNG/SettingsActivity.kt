class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // فرض کن layout شامل یک Spinner برای انتخاب زبان باشه
        setContentView(R.layout.activity_settings)

        val languageSpinner = findViewById<Spinner>(R.id.language_spinner)
        
        val languages = listOf("en" to "English", "fa" to "فارسی")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages.map { it.second })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        languageSpinner.adapter = adapter

        // تنظیم زبان فعلی روی Spinner
        val currentLang = LocaleHelper.getLanguage(this)
        val currentIndex = languages.indexOfFirst { it.first == currentLang }
        if (currentIndex >= 0) {
            languageSpinner.setSelection(currentIndex)
        }

        languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedLang = languages[position].first
                if (selectedLang != LocaleHelper.getLanguage(this@SettingsActivity)) {
                    LocaleHelper.setLocale(this@SettingsActivity, selectedLang)
                    // برای اعمال تغییر زبان Activity رو ری‌استارت کن
                    recreate()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
}

