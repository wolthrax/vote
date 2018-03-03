var total = 0;

function addNewField(){
    total++;
    console.log(total);
    $('<div>')
        .attr('class','row')
        .attr('id','row' + total)
        .append (
            $('<div>')
                .attr('class','col-25')
                .append(
                    $('<label>')
                        .attr('for','input_option_'+total)
                        .text("Вариант ответа " + total + ": ")
                ),


            $('<div>')
                .attr('class','col-70')
                .append(
                    $('<textarea />')
                        .attr('id','input_option_'+total)
                        .attr('name','input_option_'+total)
                )

        )
        .appendTo('#row-container');
    addIcons();

    var baf = total - 1;
    $('#td_del_option_' + baf).remove();
    $('#td_add_option_' + baf).remove();

}

function removeField() {
    $('#row'+total).remove();
    total--;
    console.log(total);
    addIcons();

}

function addIcons() {
    $('#row'+ total)
        .append (
            $('<div>')
                .attr('id','td_add_option_' + total)
                .attr('class','col-5')
                .append (
                    $('<a  href="#" onclick="addNewField()" class="ico_add"><img src="/img/add.png" alt="add" border="0"></a>'),
                    $('<a  href="#" onclick="if(total > 1) removeField()" class="ico_delete"><img src="/img/delete.png" alt="del" border="0"></a>')

                )
        )
}

$(document).ready(function() {
    addNewField();
});