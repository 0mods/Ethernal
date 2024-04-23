/*
 * All Rights Received
 * Copyright (c) 2024 0mods.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package team._0mods.aeternus.common.init.config

import team._0mods.aeternus.common.LOGGER

class AeternusConfig {
    interface Client
    interface Common

    var clientConfig: Client? = null
        set(value) {
            if (field != null) {
                LOGGER.warn("Client config was replaced! Old: {}, New: {}", field!!.javaClass.name, value?.javaClass?.name)
            }

            field = value
        }

    var commonConfig: Common? = null
        set(value) {
            if (field != null) {
                LOGGER.warn("Client config was replaced! Old: {}, New: {}", field!!.javaClass.name, value?.javaClass?.name)
            }

            field = value
        }
}
