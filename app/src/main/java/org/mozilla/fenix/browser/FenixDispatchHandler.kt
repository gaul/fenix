/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.fenix.browser

import android.view.KeyEvent
import mozilla.components.browser.engine.gecko.DispatchKeyHandler
import org.mozilla.fenix.components.Components

class FenixDispatchKeyHandler(
    _components: Components
) : DispatchKeyHandler {
    val components = _components

    override fun dispatchKeyEvent(event: KeyEvent) : Boolean {
        // first handle hardware keys
        when (event.getKeyCode()) {
            KeyEvent.KEYCODE_BACK -> {
                components.useCases.sessionUseCases.goBack()
                return true
            }
            KeyEvent.KEYCODE_FORWARD -> {
                components.useCases.sessionUseCases.goForward()
                return true
            }
            KeyEvent.KEYCODE_REFRESH -> {
                components.useCases.sessionUseCases.reload()
                return true
            }
        }

        // next handle control keys
        if (!event.isCtrlPressed) {
            return false
        }
        when (event.getKeyCode()) {
            KeyEvent.KEYCODE_LEFT_BRACKET -> {
                components.useCases.sessionUseCases.goBack()
                return true
            }
            KeyEvent.KEYCODE_RIGHT_BRACKET -> {
                components.useCases.sessionUseCases.goForward()
                return true
            }
            KeyEvent.KEYCODE_PERIOD -> {
                components.useCases.sessionUseCases.stopLoading()
                return true
            }
            KeyEvent.KEYCODE_R -> {
                components.useCases.sessionUseCases.reload()
                return true
            }
        }
        return false
    }
}
