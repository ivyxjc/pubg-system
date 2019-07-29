package xyz.ivyxjc.pubg.system.common.processors

import xyz.ivyxjc.pubg.system.common.entity.RawMessage

interface WorkflowProcessor {
    fun process(rawMessage: RawMessage)
}