package com.example.newstart.phone;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.service.notification.StatusBarNotification;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/**
 * Created by Ashutosh Ojha on 23,August,2021
 */
public class Contact {
    static final String TAG_Call = "Call: ";

//    private String getPhoneNumberWithThe(String clause) {
//        ContactsContract.CommonDataKinds.Phone.NUMBER
//        Log.v(TAG_Call, "getPhoneNumberWithThe() clause: " + clause);
//        String phNumber = "No Number Found";
//        String displayName = "Unknown";
//
//        Cursor cur = mContextCallManagement.getContentResolver().query(CallLog.Calls.CONTENT_URI,
//                new String[]{"number",ContactsContract.Contacts.DISPLAY_NAME}, //since we only need to read phone number. Single Col is read from SQLite DB
//                clause, //Where clause
//                null,
//                CallLog.Calls.DATE + " DESC limit 1");
//        while (cur.moveToNext()) {
//            Log.v(TAG_Call, "Iterating over Call Log to find " + clause + " call");
//            phNumber = cur.getString(0);
//            displayName = cur.getString(1);
//        }
//        cur.close();
//        return phNumber;
//    }

    static class CallManagement {
        private static CallManagement mCallManagement;
        private Context mContextCallManagement;
        private TelecomManager mTelecomsManager;
        private StatusBarNotification mStatusBarNotification;
        private PendingIntent mIntent;
        public boolean makeCall = false;
        public String callType = "";
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        private CallManagement(Context context) {
            mContextCallManagement = context;
            //register telecoms Manager
            mTelecomsManager = (TelecomManager) context
                    .getSystemService(Context.TELECOM_SERVICE);
            //register local broad cast receiver
            LocalBroadcastManager.getInstance(context).registerReceiver(mMessageReceiver,
                    new IntentFilter("Msg"));

        }
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public static CallManagement getInstance(Context context) {
            if (mCallManagement == null) {
                mCallManagement = new CallManagement(context);
            }
            return mCallManagement;
        }
        public void onAnswerCall() {
            Log.v(TAG_Call, ">>> onAnswerCall()");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Log.v(TAG_Call, "Build.VERSION.SDK_INT >= Build.VERSION_CODES.O");
                if (ActivityCompat.checkSelfPermission(mContextCallManagement, Manifest.permission.ANSWER_PHONE_CALLS) != PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG_Call, "No Phone Call Permission");
                    return;
                }
                Call.thisIsMissedCall = false; //tracking Missed Call status
                mTelecomsManager.acceptRingingCall();
                Log.v(TAG_Call, "mTelecomsManager.acceptRingingCall()");


            } else {
//                try {
//                    if (mStatusBarNotification.getNotification().actions != null) {
//                        for (Notification.Action action : mStatusBarNotification.getNotification().actions) {
//                            Log.v(Tag.Call, "" + action.title);
//                            if (action.title.toString().equalsIgnoreCase("Answer")) {
//                                Log.v(Tag.Call, "" + true);
//                                mIntent = action.actionIntent;
//                                try {
//                                    mIntent.send();
//                                } catch (PendingIntent.CanceledException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }

        }
        public void onRejectCall() {
            Log.v(TAG_Call, ">>> onRejectCall()");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                if (ActivityCompat.checkSelfPermission(mContextCallManagement, Manifest.permission.ANSWER_PHONE_CALLS) != PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG_Call, "onRejectCall no permission so returning.");
                    Toast.makeText(mContextCallManagement, "onRejectCall no permission so returning", Toast.LENGTH_SHORT).show();
                    return;
                }
                Call.thisIsMissedCall = false; //tracking Missed Call status
                mTelecomsManager.endCall();
                Log.v(TAG_Call, "mTelecomsManager.endCall()");

            } else {
//                try {
//                    if (mStatusBarNotification.getNotification().actions != null) {
//                        for (Notification.Action action : mStatusBarNotification.getNotification().actions) {
//                            Log.v(TAG_Call, "" + action.title);
//                            if (action.title.toString().equalsIgnoreCase("Dismiss")) {
//                                Log.v(Tag.Call, "" + true);
//                                mIntent = action.actionIntent;
//                                try {
//                                    mIntent.send();
//                                } catch (PendingIntent.CanceledException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

            }
        }
        public void onEndCall() {
            Log.v(TAG_Call, ">>> onEndCall()");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                if (ActivityCompat.checkSelfPermission(mContextCallManagement, Manifest.permission.ANSWER_PHONE_CALLS) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mTelecomsManager.endCall();
            } else {
//                try {
//                    if (mStatusBarNotification.getNotification().actions != null) {
//                        for (Notification.Action action : mStatusBarNotification.getNotification().actions) {
//                            Log.v(TAG_Call, "" + action.title);
//                            if (action.title.toString().equalsIgnoreCase("Hang up")) {
//                                Log.v(Tag.Call, "" + true);
//                                mIntent = action.actionIntent;
//                                try {
//                                    mIntent.send();
//                                } catch (PendingIntent.CanceledException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        }
//        public static byte[] getBytesForMissedNumberToECU(String phNUmber) {
//            Log.v(TAG_Call, ">>> getBytesForMissedNumberToECU() phNUmber: " + phNUmber);
//            byte[] bytesToReturn = new byte[16];
//            bytesToReturn[0] = 0x4C;
//            bytesToReturn[1] = 0x00;
//            bytesToReturn[2] = 0x00;
//            bytesToReturn[3] = 0x00;
//            bytesToReturn[4] = 0x00;
//            bytesToReturn[5] = 0x00;
//            bytesToReturn[6] = 0x00;
//            bytesToReturn[7] = 0x00;
//            bytesToReturn[8] = 0x00;
//            bytesToReturn[9] = 0x00;
//            bytesToReturn[10] = 0x00;
//            bytesToReturn[11] = 0x00;
//            bytesToReturn[12] = 0x00;
//            bytesToReturn[13] = 0x00;
//            bytesToReturn[14] = 0x00;
//            bytesToReturn[15] = 0x00;
//            //phNUmber = phNUmber.replaceFirst("\\+", "");// removing + from phone number Eg +919822174322
//            bytesToReturn[1] = Util.convertIntToByte(phNUmber.length(), 1)[0]; //ECU need number of char in string as well
//            int byteNumber = 2;
//            for (int i = 0; i < phNUmber.length(); i++) {
//                char digitChar = phNUmber.charAt(i);
//                //int digit = Integer.parseInt(String.valueOf(digitChar));
//                bytesToReturn[byteNumber] = Util.convertIntToByte(digitChar, 1)[0];
//                byteNumber = byteNumber + 1;
//                System.out.println("char at " + i + " index is: " + digitChar);
//            }
//            return bytesToReturn;
//        }
        private String getPhoneNumberWithThe(String clause) {
            Log.v(TAG_Call, "getPhoneNumberWithThe() clause: " + clause);
            String phNumber = "No Number Found";
            String displayName = "Unknown";

            Cursor cur = mContextCallManagement.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                    new String[]{"number", "name"}, //since we only need to read phone number. Single Col is read from SQLite DB
                    clause, //Where clause
                    null,
                    CallLog.Calls.DATE + " DESC limit 1");
            while (cur.moveToNext()) {
                Log.v(TAG_Call, "Iterating over Call Log to find " + clause + " call");
                phNumber = cur.getString(0);
                displayName = cur.getString(1);
            }
            cur.close();
            return phNumber;
        }
        //local broadcast receiver for notification
        private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
            @Override public void onReceive(Context context, Intent intent) {
                // Get extra data included in the Intent
                mStatusBarNotification = intent.getParcelableExtra("statusNotification");
                Log.v(TAG_Call, ">>> Notification Broadcast");
            }
        };
        public void activateCallService() {
            //Log.v(Tag.Call, ">>> activateCallService()");
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override public void run() {
                    if (makeCall) {
                        Log.v(TAG_Call, "If block of activateCallService");
                        if (makeCallAllowed()) return;
                    }
                    activateCallService();
                }
            }, 1000 * 3);
        }
        @RequiresApi(api = Build.VERSION_CODES.M)
        private boolean makeCallAllowed() {
            String clauseStr = "";
            if (callType.equals("Missed")) clauseStr = "type=3";
            else if (callType.equals("Rejected")) clauseStr = "type=5 OR (type=1 AND duration=0)";
            else if (callType.equals("IncomingWithZeroSec"))
                clauseStr = "type=5 OR (type=1 AND duration=0)";
            String callNumber = getPhoneNumberWithThe(clauseStr);
            if (callNumber.equals("No Number Found")) {
                Log.w(TAG_Call, "No " + callType + " Call");
                callNumber = "11111";
            } else {
                Log.v(TAG_Call, "Reach till intent");
            }
            //Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + callNumber));
            //This approach always use system telecoms service
            Uri uri = Uri.fromParts("tel", callNumber, null);
            Bundle extras = new Bundle();
            extras.putBoolean(TelecomManager.EXTRA_OUTGOING_CALL_EXTRAS, true);
            if (ActivityCompat.checkSelfPermission(mContextCallManagement, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
            mTelecomsManager.placeCall(uri, extras);
            Log.i("CallMgmt", "activateCallService placeCall: " + callNumber);
            makeCall = false;
            return false;
        }
         String getNameNumber(String clause) {
            Log.v(TAG_Call, "getPhoneNumberWithThe() clause: " + clause);
            String phNumber = "No Number Found";
            String displayName = "Unknown";

            Cursor cur = mContextCallManagement.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                    new String[]{"number", "name"}, //since we only need to read phone number. Single Col is read from SQLite DB
                    null, //Where clause
                    null,
                    CallLog.Calls.DATE + " DESC limit 1");
            while (cur.moveToNext()) {
                Log.v(TAG_Call, "Iterating over Call Log to find " + clause + " call");
                phNumber = cur.getString(0);
                displayName = cur.getString(1);
            }
            cur.close();
            return phNumber;
        }

    }




    public static class Call extends BroadcastReceiver {
        private PreferencesValues mPreferencesValues;
        public static byte[] currentCallStateBytes = new byte[]{0x4A, 0x20, 0x00};
        public static byte[] idleBytes = new byte[]{0x4A, 0x20, 0x00};
        public static byte[] activeBytes = new byte[]{0x4A, 0x20, 0x01};
        public static byte[] incomingBytes = new byte[]{0x4A, 0x20, 0x02};
        public static byte[] missedBytes = new byte[]{0x4A, 0x20, 0x03};
        public static byte[] outgoingBytes = new byte[]{0x4A, 0x20, 0x04};
        public static boolean thisIsMissedCall = false;
        @Override public void onReceive(Context context, Intent intent) {
            mPreferencesValues = PreferencesValues.getInstance(context);
            try {
                System.out.println("Receiver start");
                String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
                String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                if (state == null) {
                    Log.e(TAG_Call, "state == null so returning from onReceive method");
                    return;
                }
                if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                    Log.d(TAG_Call, "EXTRA_STATE_RINGING");
                    if (incomingNumber != null) {
                        return;
                    }
//                    MusicManagement.getInstance(context).onPause();
                    mPreferencesValues.putIsCALL_STATE_RINGING(true);



                    // Todo: Need to handle this scenario
                    // when device was still scanning incoming call hit
                    // so below write operation called although
                    // it should get called if device is connected
                    notifyECU(incomingBytes); // Incoming Call
                    thisIsMissedCall = true;
                } else if ((state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) && incomingNumber != null) {
                    //Toast.makeText(context, "Call Received State", Toast.LENGTH_SHORT).show();
                    Log.d(TAG_Call, "EXTRA_STATE_OFFHOOK");
                    mPreferencesValues.putIsCALL_STATE_OFFHOOK(true);
                    thisIsMissedCall = false;
                    if (mPreferencesValues.getIsCALL_STATE_RINGING()) {
                        notifyECU(activeBytes); // Active Call
                        Log.d(TAG_Call, "Writing Active Call on ECU");
                    } else {
                        notifyECU(outgoingBytes); // Outgoing Call
                        Log.d(TAG_Call, "Writing Outgoing Call on ECU");
                    }
                    mPreferencesValues.putIsCALL_STATE_RINGING(false);
                 String callerName=   getName(context,incomingNumber);

                } else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                    Log.d(TAG_Call, "EXTRA_STATE_IDLE");
                    if (incomingNumber != null) {
                        return;
                    }
                    mPreferencesValues.putIsCALL_STATE_RINGING(false);
                    mPreferencesValues.putIsCALL_STATE_OFFHOOK(false);
                    checkIfThisIsMissedCall();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        private void checkIfThisIsMissedCall() {
            Log.v(TAG_Call, ">>> checkIfThisIsMissedCall()");
            if (thisIsMissedCall) { //if last sent count to FW changes then only send otherwise just send state
                notifyECU(missedBytes); // Missed Call
                Log.i(TAG_Call, "This was missed call");
            } else {
                notifyECU(idleBytes); //otherwise just send state
                Log.i(TAG_Call, "This call was not Missed call");
            }
            thisIsMissedCall = false;
        }

        private void notifyECU(byte[] callType) {
            Log.v(TAG_Call, ">>> notifyECU()");
            currentCallStateBytes = callType;
//            if (isConnected && ignitionState && !isBusy) {
//                mBle.writeToBLEDevice(currentCallStateBytes); //Call
//            } else {
//                Log.w(Tag.Call, "Not sending call status.");
//            }
        }

        String getName(Context context,String phoneNumber) {

            Uri uri=Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,Uri.encode(phoneNumber));

            String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME};

            String contactName="";
            Cursor cursor=context.getContentResolver().query(uri,projection,null,null,null);

            if (cursor != null) {
                if(cursor.moveToFirst()) {
                    contactName=cursor.getString(0);
                }
                cursor.close();
            }
            return contactName;

        }
    }

    static class PreferencesValues {
        private static PreferencesValues mPreferencesValues;
        private Context mContext;
        public static final String DEFAULT_NAME = "default_settings";
        private SharedPreferences mSharedPreferences;
        private SharedPreferences.Editor mEditor;
        public static final String MAC_ID = "MAC_ID";
        public static final String RANDOM_NUMBER = "RANDOM_NUMBER";
        public static final String DEFAULT_MACID = "DEFAULT_MACID";
        public static final String DEFAULT_NUMBER = "DEFAULT_NUMBER";
        public static final String DEFAULT_CALL_STATE_RINGING = "DEFAULT_CALL_STATE_RINGING";
        public static final String DEFAULT_CALL_STATE_OFFHOOK = "DEFAULT_CALL_STATE_OFFHOOK";
        private PreferencesValues(Context context) {
            mContext = context;
            mSharedPreferences = mContext.getSharedPreferences(DEFAULT_NAME, mContext.MODE_PRIVATE);
            mEditor = mSharedPreferences.edit();
        }
        public static synchronized PreferencesValues getInstance(Context context) {
            mPreferencesValues = new PreferencesValues(context);
            return mPreferencesValues;
        }
        public void putMacIdPreferences(String macId) {
            mEditor.putString(MAC_ID, macId);
            mEditor.commit();
        }
        public String getStoredMacIdPreferences() {
            return mSharedPreferences.getString(MAC_ID, DEFAULT_MACID);
        }
        public void putRandomNumberPreferences(String randomNumberHex) {
            mEditor.putString(RANDOM_NUMBER, randomNumberHex);
            mEditor.commit();
        }
        public String getStoredRandomNumberPreferences() {
            return mSharedPreferences.getString(RANDOM_NUMBER, DEFAULT_NUMBER);
        }
        public void putIsPairingIsDone(boolean isPaired) {
            mEditor.putBoolean("isPaired", isPaired);
            mEditor.commit();
        }
        public boolean getIsPairingIsDone() {
            return mSharedPreferences.getBoolean("isPaired", false);
        }
        public void putIsCALL_STATE_RINGING(boolean CALL_STATE_RINGING) {
            mEditor.putBoolean(DEFAULT_CALL_STATE_RINGING, CALL_STATE_RINGING);
            mEditor.commit();
        }
        public boolean getIsCALL_STATE_RINGING() {
            return mSharedPreferences.getBoolean(DEFAULT_CALL_STATE_RINGING, false);
        }
        public void putIsCALL_STATE_OFFHOOK(boolean CALL_STATE_OFFHOOK) {
            mEditor.putBoolean(DEFAULT_CALL_STATE_OFFHOOK, CALL_STATE_OFFHOOK);
            mEditor.commit();
        }
        public boolean getIsCALL_STATE_OFFHOOK() {
            return mSharedPreferences.getBoolean(DEFAULT_CALL_STATE_OFFHOOK, false);
        }

    }
    static class MusicManagement {
        public static final String SERVICE = "com.android.music.musicservicecommand";
        public static final String NAME = "command";
        static boolean isMusicDataUpdated = true;
        static boolean currentMusicStatus = false;
        private static MusicManagement musicManagement;
        private Context mContextMusicManagement;
        private MusicManagement(Context context) {
            mContextMusicManagement = context;
        }
        public static MusicManagement getInstance(Context context) {
            if (musicManagement == null) {
                musicManagement = new MusicManagement(context);
            }
            return musicManagement;
        }

        public void onVolumeIncrease() {
            Log.d(TAG_Call, "onVolumeIncrease");
            Toast.makeText(mContextMusicManagement,"onVolumeIncrease",Toast.LENGTH_SHORT).show();
            AudioManager audioManager = (AudioManager) mContextMusicManagement.getSystemService(Context.AUDIO_SERVICE);
            audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
            }


        public void onVolumeDecrease() {
            Log.d(TAG_Call, "onVolumeDecrease");
            Toast.makeText(mContextMusicManagement,"onVolumeDecrease",Toast.LENGTH_SHORT).show();
            AudioManager audioManager = (AudioManager) mContextMusicManagement.getSystemService(Context.AUDIO_SERVICE);
            audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
        }

        public void onPlay() {
            Intent i = new Intent(SERVICE);
            i.putExtra(NAME, "play");
            mContextMusicManagement.sendBroadcast(i);
            isMusicDataUpdated = true;
        }
        public void onPause() {
            Intent i = new Intent(SERVICE);
            i.putExtra(NAME, "pause");
            mContextMusicManagement.sendBroadcast(i);
            isMusicDataUpdated = true;
        }
        public void onStop() {
            Intent i = new Intent(SERVICE);
            i.putExtra(NAME, "stop");
            mContextMusicManagement.sendBroadcast(i);
            isMusicDataUpdated = true;
        }
        public void onNextTrack() {
            Intent i = new Intent(SERVICE);
            i.putExtra(NAME, "next");
            mContextMusicManagement.sendBroadcast(i);
        }
        public void onPreviousTrack() {
            Intent i = new Intent(SERVICE);
            i.putExtra(NAME, "previous");
            mContextMusicManagement.sendBroadcast(i);
        }
        public void activateMusicTracking() {
            AudioManager manager = (AudioManager) mContextMusicManagement.getSystemService(Context.AUDIO_SERVICE);
            boolean preState = currentMusicStatus;
            currentMusicStatus = manager.isMusicActive();
            if (preState != currentMusicStatus) {
                isMusicDataUpdated = true;
            }
            final Handler gsmHandler = new Handler(Looper.getMainLooper());
            gsmHandler.postDelayed(new Runnable() {
                @Override public void run() {
                    activateMusicTracking();
                }
            }, 1000 * 1);
        }
        static void createMusicStatusData() {
//            Log.v(TAG_Call, ">>> writeMusicStatusToFW()");
//            AudioManager manager = (AudioManager) mBle.mContext.getSystemService(Context.AUDIO_SERVICE);
//            byte currentStatus = (byte) (manager.isMusicActive() ? 0x01 : 0x00);
//            notifyECU(currentStatus);
        }
        private static void notifyECU(byte status) {
//            Log.i(TAG_Call, ">>> notifyECU()");
//            if (isConnected && ignitionState && !isBusy) {
//                mBle.writeToBLEDevice(new byte[]{0x4A, 0x30, status}); // Music
//                isMusicDataUpdated = false;
//            } else {
//                Log.w(Tag.Music, "Not sending Music status to ECU");
//            }
        }
    }
    //</editor-fold>

}


