/*
 * All Rights Received
 * Copyright (c) 2024 0mods.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package team._0mods.aeternus.api.plugin

import team._0mods.aeternus.api.registry.ResearchRegistry
import team._0mods.aeternus.api.registry.ResearchTriggerRegistry
import team._0mods.aeternus.api.registry.SpellRegistry

/**
 * Starts registration of the current plugin.
 * Works only with [AeternusPlugin]
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@MustBeDocumented
annotation class AeternusPluginRegistry(
    /**
     * Plugin's mod id for registry initialization
     *
     * @return [String] of mod id
     */
    val modId: String
)

/**
 * Plugin base interface.
 * For registry your plugin use [AeternusPluginRegistry] with plugin class
 *
 * For a more stable plugin, I suggest using Kotlin, not Java or any other JVM-like language
 */
interface AeternusPlugin {
    /**
     * This is a research registration.
     *
     * @param [reg] returns [ResearchRegistry] to register research
     */
    fun registerResearch(reg: ResearchRegistry) {}

    /**
     * This is a research trigger registration.
     *
     * @param [reg] returns [ResearchTriggerRegistry] to register research trigger
     */
    fun registerResearchTriggers(reg: ResearchTriggerRegistry) {}

    /**
     * This is a spell registration
     *
     * @param [reg] returns [SpellRegistry] in order to register spells
     */
    fun registerSpells(reg: SpellRegistry) {}

//    /**
//     * This is a material registration
//     *
//     * @param [reg] returns [MaterialRegistry] to register material
//     */
//    @ApiStatus.NonExtendable
//    @ApiStatus.Internal
//    @ApiStatus.AvailableSince("nan")
//    fun registerMaterial(reg: MaterialRegistry) {
//        throw IllegalAccessException("This function is not available in current version. Please, wait realisation!")
//    }
}
