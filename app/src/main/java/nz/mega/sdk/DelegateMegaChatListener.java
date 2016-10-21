/*
 * (c) 2013-2015 by Mega Limited, Auckland, New Zealand
 *
 * This file is part of the MEGA SDK - Client Access Engine.
 *
 * Applications using the MEGA API must present a valid application key
 * and comply with the the rules set forth in the Terms of Service.
 *
 * The MEGA SDK is distributed in the hope that it will be useful,\
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * @copyright Simplified (2-clause) BSD License.
 * You should have received a copy of the license along with this
 * program.
 */
package nz.mega.sdk;

class DelegateMegaChatListener extends MegaChatListener {

    MegaChatApiJava megaChatApi;
    MegaChatListenerInterface listener;

    DelegateMegaChatListener(MegaChatApiJava megaApi, MegaChatListenerInterface listener) {
        this.megaChatApi = megaApi;
        this.listener = listener;
    }

    MegaChatListenerInterface getUserListener() {
        return listener;
    }

    @Override
    public void onChatListItemUpdate(MegaChatApi api, MegaChatListItem item)
    {
        if (listener != null) {
            final MegaChatListItem megaChatListItem = item.copy();
            megaChatApi.runCallback(new Runnable() {
                public void run() {
                    listener.onChatListItemUpdate(megaChatApi, megaChatListItem);
                }
            });
        }
    }

    @Override
    public void onChatRoomUpdate(MegaChatApi api, MegaChatRoom chat)
    {
        if (listener != null) {
            final MegaChatRoom megaChatRoom = chat.copy();
            megaChatApi.runCallback(new Runnable() {
                public void run() {
                    listener.onChatRoomUpdate(megaChatApi, megaChatRoom);
                }
            });
        }
    }
}