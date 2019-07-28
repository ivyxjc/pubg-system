package xyz.ivyxjc.pubg.system.common.processors

import xyz.ivyxjc.pubg.system.common.model.TransmissionModel

interface WorkflowProcessor {
    fun process(transmissionModel: TransmissionModel)
}