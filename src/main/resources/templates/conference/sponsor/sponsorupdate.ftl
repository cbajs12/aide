<#-- @ftlvariable name="sponsor" type="com.pjwards.aide.domain.Sponsor" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="nameError" type="java.util.Optional<String>" -->
<#-- @ftlvariable name="nameSuccess" type="java.util.Optional<String>" -->
<#-- @ftlvariable name="avatarSuccess" type="java.util.Optional<String>" -->
<#-- @ftlvariable name="slugSuccess" type="java.util.Optional<String>" -->
<#-- @ftlvariable name="slugError" type="java.util.Optional<String>" -->
<#-- @ftlvariable name="rankSuccess" type="java.util.Optional<String>" -->
<#-- @ftlvariable name="rankError" type="java.util.Optional<String>" -->
<#-- @ftlvariable name="urlSuccess" type="java.util.Optional<String>" -->
<#-- @ftlvariable name="descriptionSuccess" type="java.util.Optional<String>" -->

<@layout.extends name="layouts/default.ftl">
    <@layout.put block="head">
    <title> Sponsor :: Update</title>

    <!-- MetisMenu CSS -->
    <link href="/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="/lib/sb-admin/dist/css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/lib/sb-admin/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet"
          type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    </@layout.put>

    <@layout.put block="header" type="prepend">
        <@layout.extends name="layouts/header/admin.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="contents">
    <div class="row">
        <div class="col-lg-12">
        <#if nameError??>
            <div class="alert alert-dismissable alert-danger text-center">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <p>${nameError}</p>
            </div>
        </#if>
        <#if nameSuccess??>
            <div class="alert alert-dismissable alert-success text-center">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <p>${nameSuccess}</p>
            </div>
        </#if>
        <#if avatarSuccess??>
            <div class="alert alert-dismissable alert-success text-center">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <p>${avatarSuccess}</p>
            </div>
        </#if>
        <#if slugSuccess??>
            <div class="alert alert-dismissable alert-success text-center">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <p>${slugSuccess}</p>
            </div>
        </#if>
        <#if slugError??>
            <div class="alert alert-dismissable alert-danger text-center">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <p>${slugError}</p>
            </div>
        </#if>
        <#if rankError??>
            <div class="alert alert-dismissable alert-danger text-center">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <p>${rankError}</p>
            </div>
        </#if>
        <#if rankSuccess??>
            <div class="alert alert-dismissable alert-success text-center">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <p>${rankSuccess}</p>
            </div>
        </#if>
        <#if urlSuccess??>
            <div class="alert alert-dismissable alert-success text-center">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <p>${urlSuccess}</p>
            </div>
        </#if>
        <#if descriptionSuccess??>
            <div class="alert alert-dismissable alert-success text-center">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <p>${descriptionSuccess}</p>
            </div>
        </#if>
        </div>
    </div>

    <div id="wrapper">
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Sponsor Update
                    </h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <form role="form" action="" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <div class="form-group <#if errorName??>has-error</#if>"">
                        <label>Name</label>
                        <input class="form-control" type="text" name="name" id="name" placeholder="Name" value="${sponsor.name}" required>
                        </div>
                        <div class="form-group <#if errorSlug??>has-error</#if>"">
                        <label>Slug</label>
                        <input class="form-control" type="text" name="slug" id="slug" placeholder="Slug" value="${sponsor.slug}" required>
                        </div>
                        <div class="form-group <#if errorUrl??>has-error</#if>"">
                        <label>Url</label>
                        <input type="text" class="form-control" id="basic-url" name="url" value="${sponsor.url}">
                        </div>
                        <div class="form-group <#if errorUrl??>has-error</#if>"">
                        <label>Rank(1 ~ 100)</label>
                        <input class="form-control" type="number" name="rank" id="rank" min="1" max="100" value="${sponsor.rank}" required>
                        </div>
                        <div class="form-group">
                            <label>Description</label>
                            <textarea class="form-control" id="summernote" name="description" placeholder=""><#if sponsor.description?? && sponsor.description != "">${sponsor.description}</#if></textarea>
                        </div>
                        <div class="form-group <#if errorFiles??>has-error</#if>"">
                        <label>Image</label>
                        <img id="avatar" src="<#if sponsor.assets??>${sponsor.assets.realPath}<#else>/basic/img/user.png</#if>" alt="picture" class="col-xs-1 col-sm-1 col-md-1 col-lg-1">
                        <input type="text" readonly="" class="form-control floating-label" placeholder="Browse...">
                        <input type="file" name="assets" id="inputFile" multiple="">
                        </div>
                        <button type="submit" class="btn btn-info">Update</button>
                    </form>
                </div>
            </div>
    <!-- /.col-lg-12-->
        </div>
    <!-- /.row (nested) -->
    </div>
    <!-- /#page-wrapper -->
    <!-- /#wrapper -->
    </@layout.put>

    <@layout.put block="footer" type="replace">
        <@layout.extends name="layouts/footer.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="script">
    <!-- Metis Menu Plugin JavaScript -->
    <script src="/bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/lib/sb-admin/dist/js/sb-admin-2.js"></script>

    <script>
        $(document).ready(function () {
            $('#summernote').summernote({
                height: 300,                 // set editor height
                minHeight: null,             // set minimum height of editor
                maxHeight: null,             // set maximum height of editor
                focus: true,                 // set focus to editable area after initializing summernote
                callbacks: {
                    onImageUpload: function(files) {
                        // upload image to server and create imgNode...
                        for(var i = 0; i < files.length; i++)
                            sendFile(files[i]);
                    }
                }
            });

            function sendFile(file) {
                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");
                var data = new FormData();
                data.append("file", file);
                $.ajax({
                    url: "/upload/images",
                    data: data,
                    cache: false,
                    contentType: false,
                    processData: false,
                    type: 'POST',
                    beforeSend: function(xhr) {
                        // here it is
                        xhr.setRequestHeader(header, token);
                    },
                    success: function(data) {
                        $('#summernote').summernote('insertImage', data.assets.realPath, data.assets.name);
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        console.log(textStatus + " " + errorThrown);
                    }

                })
            }
        });

        $(function (){
            $('input[type="file"]').change(function(){
                $("#assets").empty();
                for(var i=0; this.files.length; i++) {
                    var file = this.files[i];
                    $("#assets").append('<option>'+file.name+'</option>');
                }
            });
        });
    </script>

    </@layout.put>
</@layout.extends>