/*
 * All Rights Received
 * Copyright (c) 2024 0mods.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package team._0mods.aeternus.api.client

import net.minecraft.client.Minecraft
import team._0mods.aeternus.service.PlatformHelper

object TickHandler {
    private var clientTicks = 0
    private var serverTicks = 0

    val partialTicks: Float = Minecraft.getInstance().deltaFrameTime
    val currentTicks: Int = if (PlatformHelper.isLogicalClient()) clientTicks else serverTicks

    internal fun clientTick() {
        clientTicks++
    }

    internal fun serverTicks() {
        serverTicks++
    }
}
