 $(document).ready(function() {
    $('#observationForm').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            date: {
                validators: {
                       notEmpty: {
                        message: 'Please supply your first name'
                    }
                }
            },
            inputobref: {
                validators: {
                    notEmpty: {
                        message: 'Please supply your last name'
                    }
                }
            },
           }
        })
        .on('success.form.bv', function(e) {
                $('#observationForm').data('bootstrapValidator').resetForm();

            // Prevent form submission
            e.preventDefault();

            // Get the form instance
            var $form = $(e.target);

            // Get the BootstrapValidator instance
            var bv = $form.data('bootstrapValidator');

            // Use Ajax to submit form data
            $.post($form.attr('action'), $form.serialize(), function(result) {
                console.log(result);
            }, 'json');
        });
});


