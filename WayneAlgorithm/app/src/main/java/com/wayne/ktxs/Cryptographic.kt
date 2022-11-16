package com.wayne.ktxs

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.annotation.RequiresApi
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.KeyGenerator
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

fun ByteArray.encryptAES(encryptKey: SecretKey): ByteArray? = try {
    Cipher.getInstance("AES/CBC/PKCS7PADDING").let {
        it.init(Cipher.ENCRYPT_MODE, encryptKey)
        it.iv + it.doFinal(this)
    }
} catch (e: NoSuchPaddingException) {
    e.printStackTrace()
    null
} catch (e: NoSuchAlgorithmException) {
    e.printStackTrace()
    null
} catch (e: InvalidAlgorithmParameterException) {
    e.printStackTrace()
    null
} catch (e: InvalidKeyException) {
    e.printStackTrace()
    null
} catch (e: BadPaddingException) {
    e.printStackTrace()
    null
} catch (e: IllegalBlockSizeException) {
    e.printStackTrace()
    null
}

fun ByteArray.decryptAES(decryptKey: SecretKey): ByteArray? = try {
    val IV_BLOCK_SIZE = 16
    val iv = copyOfRange(0, IV_BLOCK_SIZE)
    val decryptData = copyOfRange(IV_BLOCK_SIZE, size)
    Cipher.getInstance("AES/CBC/PKCS7PADDING").let {
        it.init(Cipher.DECRYPT_MODE, decryptKey, IvParameterSpec(iv))
        it.doFinal(decryptData)
    }
} catch (e: NoSuchPaddingException) {
    e.printStackTrace()
    null
} catch (e: NoSuchAlgorithmException) {
    e.printStackTrace()
    null
} catch (e: InvalidAlgorithmParameterException) {
    e.printStackTrace()
    null
} catch (e: InvalidKeyException) {
    e.printStackTrace()
    null
} catch (e: BadPaddingException) {
    e.printStackTrace()
    null
} catch (e: IllegalBlockSizeException) {
    e.printStackTrace()
    null
}

@RequiresApi(Build.VERSION_CODES.M)
fun String.getKeyGeneratorByAlias(): KeyGenerator =
    KeyGenerator.getInstance("AES", "AndroidKeyStore").apply {
        init(
            KeyGenParameterSpec.Builder(
                this@getKeyGeneratorByAlias,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)//AES_CBC
                .setUserAuthenticationRequired(false)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)// use the same paddings as encrypt/descrypt functions above
                .build()
        )
    }
