<#list messages as e>
    /**
     * ${e.title}
     * ${e.memo!}
     */
    @Size(max = ${e.size})
    <#if e.isNull == 'Y'>
    <#if e.dateType == 'String'>
    @NotBlank
    <#else>
    @NotNull
    </#if>
    </#if>
    private ${e.dateType} ${e.name};

</#list>

