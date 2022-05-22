package com.example.newstart.encryption

import android.os.Build
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.security.keystore.KeyProtection
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R
import java.security.KeyStore
import java.security.KeyStoreException
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec


/**
 * Created by Ashutosh Ojha on 17,May,2022
 */
class EncryptionActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val unrestrictedPublicKey: PublicKey =
//            KeyFactory.getInstance(publicKey.getAlgorithm()).generatePublic(
//                X509EncodedKeySpec(publicKey.getEncoded())
//            )

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn = findViewById<Button>(R.id.btn)
        btn1.setOnClickListener {
            //   keyFile()
            val pair = encryption("FirstAlias", "\n" +
                    "Mr Jakhar is in Delhi and has already met BJP chief JP Nadda.\n" +"\n" +
                    "Mr Jakhar is in Delhi and has already met BJP chief JP Nadda.\n" +
                    "\n" +
                    "\n" +
                    "Sunil Jakhar, senior Congress leader and former chief of its Punjab unit, has joined rival BJP days after quitting the grand old party. The former Congress leader recently quit Congress weeks after he was issued a show-cause notice by the Congress leadership over his criticism of former Chief Minister Charanjit Singh Channi. Congress's coterie has now turned into a gang, he said while addressing the media with BJP chief JP Nadda by his side.\n" +
                    "Sources close to Mr Jakhar said that he may be nominated for the Rajya Sabha and would be given some responsibility in Punjab and also would be instrumental in bringing more disgruntled Congress leaders into the BJP fold. He had recently indicated that he wanted to remain relevant in Punjab.\n" +
                    "ADVERTISEMENT BY ADRECOVER\n" +
                    "\n" +
                    "\n" +
                    "Expressing sorrow over breaking ties with Congress, which he called \"family\", he said he was sidelined because he pointed out that the party can't treat Punjab in percentages and divide people over caste. \"You can remove Sunil Jakhar from a party post but can't silence him,\" he said while on stage with Mr Nadda.\n" +
                    "\n" +
                    "\"I had a 50-year-old relationship with congress, my family has been with the party for three generations since 1972. I considered it family,\" he said, claiming that he quit Congress, not because of any personal dispute, but \"fundamental issues\" with the party. There is an element of casteism in the Congress, but BJP has equality for all, he said.\n" +
                    "\n" +
                    "JP Nadda, who personally appeared on stage to induct the former Congress stalwart into BJP, clearly said that he would play a critical role for BJP in Punjab. \n" +
                    "\n" +
                    "Mr Jakhar is a prominent non-Sikh face of Punjab and is respected across party lines. He holds sway over many disgruntled Congress leaders. Mr Jakhar is the BJP's second big catch after Captain Amarinder Singh allied with BJP. He is likely to help the party in the upcoming assembly polls in neighbouring Himachal Pradesh.\n" +
                    "\n" +
                    "Sunil Jakhar's father, Balram Jakhar, was the Lok Sabha speaker from 1980 to 1989. A former governor of Madhya Pradesh as well, Balram Jakhar was an Indira Gandhi loyalist who also served as agriculture minister in the Narasimha Rao government from 1991 to 1996.\n" +
                    "\n" +
                    "Sunil Jakhar was at 48 years of age elected as MLA from Abohar in the Fazilka district for the first time. He was re-elected as MLA in both the 2007 and 2012 Punjab polls. Between 2012 and 2017, he was the leader of the opposition in the state assembly. In 2017, he lost in state polls but was elected as Gurdaspur MP in the Lok Sabha by-poll after the death of Vinod Khanna.\n" +
                    "ADVERTISEMENT BY ADRECOVER\n" +
                    "\n" +
                    "\n" +
                    "In a dramatic public resignation, while the party was holding a three-day brainstorming session in Rajasthan, Mr Jakhar had said \"Goodbye and good luck, Congress\" in a Facebook live video after scathing criticism of former party colleagues who led the action against him.\n" +
                    "\n" +
                    "Last month, the Congress' disciplinary panel had recommended that Mr Jakhar be suspended from the party for two years and removed from all posts.\n" +
                    "\n" +
                    "ALSO READ\n" +
                    "Punjab Ex-Congress Chief An Asset, Party Shouldn't Lose Him: Navjot Sidhu\n" +
                    "Punjab Ex-Congress Chief An Asset, Party Shouldn't Lose Him: Navjot Sidhu\n" +
                    "After Quitting, Punjab Ex-Congress Chief Vs Harish Rawat On Revolt\n" +
                    "After Quitting, Punjab Ex-Congress Chief Vs Harish Rawat On Revolt\n" +
                    "\"Good Luck, Goodbye\": Ex Punjab Congress Chief Quits Party, Guns Blazing\n" +
                    "\"Good Luck, Goodbye\": Ex Punjab Congress Chief Quits Party, Guns Blazing\n" +
                    "The five-member Committee is chaired by senior party leader AK Antony. The meeting on what action should be taken against Mr Jakhar was attended by members Tariq Anwar, JP Aggarwal and G Parameswar, besides Mr Antony.\n" +
                    "\n" +
                    "Ambika Soni, seen as Mr Jakhar's bete noire, was not present during the meeting.\n" +
                    "\n" +
                    "The former Punjab Congress chief had criticised former Chief Minister Channi and termed him a liability for the party after it lost to the Aam Aadmi Party (AAP) in Punjab.\n" +
                    "ADVERTISEMENT BY ADRECOVER\n" +
                    "\n" +
                    "\n" +
                    "Mr Jakhar had also been critical of the party leadership for picking Mr Channi as Chief Ministerial candidate for the elections and had hit out at Ms Soni for supposedly scuttling his chances of being picked as Chief Minister after Amarinder Singh's ouster.\n" +
                    "\n" +
                    "\"It is extremely unfortunate that today I have had to leave the Congress after 50 years because they put me in the \"katghara\" (witness box), and questioned my intent,\" Mr Jakhar said today.\n" +
                    "\n" +
                    "Amid the crisis in Punjab Congress after Amarinder Singh's resignation in September, Ms Soni had said she turned down an offer for the Chief Minister post and told the leadership that only a Sikh should be chosen for the job.\n" +
                    "\n" +
                    "\n" +
                    "\"You did not sever ties with me, you broke my heart, you issued a notice, were you all ashamed to talk to me? I would have given all the answers,\" he had said in the live video.\n" +
                    "\n" +
                    "Comments\n" +
                    "Responding to Mr Jakhar's remarks, former Punjab Congress chief Navjot Singh Sidhu had tweeted the party should not lose him. \"The Congress should not loose (sic) #sunilkjakhar .... Is an asset worth his weight in gold .... Any differences can be resolved on the table,\" he said"+
                    "\n" +
                    "\n" +
                    "Sunil Jakhar, senior Congress leader and former chief of its Punjab unit, has joined rival BJP days after quitting the grand old party. The former Congress leader recently quit Congress weeks after he was issued a show-cause notice by the Congress leadership over his criticism of former Chief Minister Charanjit Singh Channi. Congress's coterie has now turned into a gang, he said while addressing the media with BJP chief JP Nadda by his side.\n" +
                    "Sources close to Mr Jakhar said that he may be nominated for the Rajya Sabha and would be given some responsibility in Punjab and also would be instrumental in bringing more disgruntled Congress leaders into the BJP fold. He had recently indicated that he wanted to remain relevant in Punjab.\n" +
                    "ADVERTISEMENT BY ADRECOVER\n" +
                    "\n" +
                    "\n" +
                    "Expressing sorrow over breaking ties with Congress, which he called \"family\", he said he was sidelined because he pointed out that the party can't treat Punjab in percentages and divide people over caste. \"You can remove Sunil Jakhar from a party post but can't silence him,\" he said while on stage with Mr Nadda.\n" +
                    "\n" +
                    "\"I had a 50-year-old relationship with congress, my family has been with the party for three generations since 1972. I considered it family,\" he said, claiming that he quit Congress, not because of any personal dispute, but \"fundamental issues\" with the party. There is an element of casteism in the Congress, but BJP has equality for all, he said.\n" +
                    "\n" +
                    "JP Nadda, who personally appeared on stage to induct the former Congress stalwart into BJP, clearly said that he would play a critical role for BJP in Punjab. \n" +
                    "\n" +
                    "Mr Jakhar is a prominent non-Sikh face of Punjab and is respected across party lines. He holds sway over many disgruntled Congress leaders. Mr Jakhar is the BJP's second big catch after Captain Amarinder Singh allied with BJP. He is likely to help the party in the upcoming assembly polls in neighbouring Himachal Pradesh.\n" +
                    "\n" +
                    "Sunil Jakhar's father, Balram Jakhar, was the Lok Sabha speaker from 1980 to 1989. A former governor of Madhya Pradesh as well, Balram Jakhar was an Indira Gandhi loyalist who also served as agriculture minister in the Narasimha Rao government from 1991 to 1996.\n" +
                    "\n" +
                    "Sunil Jakhar was at 48 years of age elected as MLA from Abohar in the Fazilka district for the first time. He was re-elected as MLA in both the 2007 and 2012 Punjab polls. Between 2012 and 2017, he was the leader of the opposition in the state assembly. In 2017, he lost in state polls but was elected as Gurdaspur MP in the Lok Sabha by-poll after the death of Vinod Khanna.\n" +
                    "ADVERTISEMENT BY ADRECOVER\n" +
                    "\n" +
                    "\n" +
                    "In a dramatic public resignation, while the party was holding a three-day brainstorming session in Rajasthan, Mr Jakhar had said \"Goodbye and good luck, Congress\" in a Facebook live video after scathing criticism of former party colleagues who led the action against him.\n" +
                    "\n" +
                    "Last month, the Congress' disciplinary panel had recommended that Mr Jakhar be suspended from the party for two years and removed from all posts.\n" +
                    "\n" +
                    "ALSO READ\n" +
                    "Punjab Ex-Congress Chief An Asset, Party Shouldn't Lose Him: Navjot Sidhu\n" +
                    "Punjab Ex-Congress Chief An Asset, Party Shouldn't Lose Him: Navjot Sidhu\n" +
                    "After Quitting, Punjab Ex-Congress Chief Vs Harish Rawat On Revolt\n" +
                    "After Quitting, Punjab Ex-Congress Chief Vs Harish Rawat On Revolt\n" +
                    "\"Good Luck, Goodbye\": Ex Punjab Congress Chief Quits Party, Guns Blazing\n" +
                    "\"Good Luck, Goodbye\": Ex Punjab Congress Chief Quits Party, Guns Blazing\n" +
                    "The five-member Committee is chaired by senior party leader AK Antony. The meeting on what action should be taken against Mr Jakhar was attended by members Tariq Anwar, JP Aggarwal and G Parameswar, besides Mr Antony.\n" +
                    "\n" +
                    "Ambika Soni, seen as Mr Jakhar's bete noire, was not present during the meeting.\n" +
                    "\n" +
                    "The former Punjab Congress chief had criticised former Chief Minister Channi and termed him a liability for the party after it lost to the Aam Aadmi Party (AAP) in Punjab.\n" +
                    "ADVERTISEMENT BY ADRECOVER\n" +
                    "\n" +
                    "\n" +
                    "Mr Jakhar had also been critical of the party leadership for picking Mr Channi as Chief Ministerial candidate for the elections and had hit out at Ms Soni for supposedly scuttling his chances of being picked as Chief Minister after Amarinder Singh's ouster.\n" +
                    "\n" +
                    "\"It is extremely unfortunate that today I have had to leave the Congress after 50 years because they put me in the \"katghara\" (witness box), and questioned my intent,\" Mr Jakhar said today.\n" +
                    "\n" +
                    "Amid the crisis in Punjab Congress after Amarinder Singh's resignation in September, Ms Soni had said she turned down an offer for the Chief Minister post and told the leadership that only a Sikh should be chosen for the job.\n" +
                    "\n" +
                    "\n" +
                    "\"You did not sever ties with me, you broke my heart, you issued a notice, were you all ashamed to talk to me? I would have given all the answers,\" he had said in the live video.\n" +
                    "\n" +
                    "Comments\n" +
                    "Responding to Mr Jakhar's remarks, former Punjab Congress chief Navjot Singh Sidhu had tweeted the party should not lose him. \"The Congress should not loose (sic) #sunilkjakhar .... Is an asset worth his weight in gold .... Any differences can be resolved on the table,\" he said")
            val dec = decryption("FirstAlias", pair.first, pair.second)

            val pair1 = encryption("FirstAliasOne", "DataOneTwo")
            val dec1 = decryption("FirstAliasOne", pair1.first, pair1.second)

            val list = getAllAliasesInTheKeystore()
        }

        btn.setOnClickListener {

        }


    }

    private fun decryption(alias: String, encryption: ByteArray?, iv: ByteArray): String {
        val keyStore = KeyStore.getInstance("AndroidKeyStore")
        keyStore.load(null)

        val secretKeyEntry = keyStore
            .getEntry(alias, null) as KeyStore.SecretKeyEntry

        val secretKey = secretKeyEntry.secretKey;


        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        val spec = GCMParameterSpec(128, iv)

        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)

        val decodedData = cipher.doFinal(encryption)


        return String(decodedData, Charsets.UTF_8)
    }

    @Throws(KeyStoreException::class)
    private fun getAllAliasesInTheKeystore(): ArrayList<String?>? {
        val keyStore = KeyStore.getInstance("AndroidKeyStore")
        keyStore.load(null)
        return Collections.list(keyStore.aliases())
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun encryption(alias: String, input: String): Pair<ByteArray, ByteArray> {
        val keyGenerator: KeyGenerator = KeyGenerator
            .getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")

        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            alias,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .build()

        keyGenerator.init(keyGenParameterSpec)
        val secretKey = keyGenerator.generateKey()

        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)

        val iv = cipher.iv
        val encryption = cipher.doFinal(input.toByteArray())

        return Pair(encryption, iv)


    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun keyFile() {


        val keyGenerator = KeyGenerator
            .getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            "key1",
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .build()

        keyGenerator.init(keyGenParameterSpec);


        val keyStore: KeyStore = KeyStore.getInstance("AndroidKeyStore")
        keyStore.load(null)
        keyStore.setEntry(
            "key1",
            KeyStore.SecretKeyEntry(keyGenerator.generateKey()),
            KeyProtection.Builder(KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                //.setBlockMode(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .build()
        )
        // Key imported, obtain a reference to it.
        // Key imported, obtain a reference to it.
        val keyStoreKey: SecretKey = keyStore.getKey("key1", null) as SecretKey
        // The original key can now be discarded.

        // The original key can now be discarded.
        val cipher: Cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, keyStoreKey)
    }
}