package com.dudu.adbtools

import com.funny.data_saver.core.DataSaverInterface
import com.funny.data_saver_data_store.DataSaverDataStorePreferences

actual object AppConfig{
    actual val dataSaver: DataSaverInterface
        get() = DataSaverDataStorePreferences(appCtx.dataStore)
}
