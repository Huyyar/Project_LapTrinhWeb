function handleUpQty(id, qty){
    fetch(`up-qty-item?id=${id}&qty=${qty}`)
        .then(response =>{
            if(response.ok){
                location.reload();
            }
        })
        .catch(e => console.log(e));
}
function handleDeleteItem(id){
    fetch(`delete-item?id=${id}`)
        .then(response =>{
            if(response.ok){
                location.reload();
            }
        })
        .catch(e => console.log(e));
}
function handleChooseItem(id){
    fetch(`choose-item?id=${id}`)
        .then(response =>{
            if(response.ok){
                location.reload();
            }
        })
        .catch(e => console.log(e));
}
function handleDeleteChosenItems(){
    fetch('delete-chosen-items')
        .then(response =>{
            if(response.ok){
                location.reload();
            }
        })
        .catch(e => console.log(e));
}
function handleChooseAllItem(){
    fetch('choose-all-item')
        .then(response =>{
            if(response.ok){
                location.reload();
            }
        })
        .catch(e => console.log(e));
}