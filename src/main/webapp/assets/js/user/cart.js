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
function handleChoseItem(id){
    fetch(`chose-item?id=${id}`)
        .then(response =>{
            if(response.ok){
                location.reload();
            }
        })
        .catch(e => console.log(e));
}
function handleDeleteAllItem(){
    fetch('delete-all-item')
        .then(response =>{
            if(response.ok){
                location.reload();
            }
        })
        .catch(e => console.log(e));
}
function handleChoseAllItem(){
    fetch('chose-all-item')
        .then(response =>{
            if(response.ok){
                location.reload();
            }
        })
        .catch(e => console.log(e));
}